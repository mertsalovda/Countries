package ru.mertsalovda.countries.viewmodels

import android.annotation.SuppressLint
import androidx.lifecycle.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.mertsalovda.countries.App
import ru.mertsalovda.countries.models.data.Country
import ru.mertsalovda.countries.repositories.CountryRepository
import ru.mertsalovda.countries.repositories.api.ApiUtils

class MainViewModel : ViewModel() {

    private val repository: CountryRepository

    var countries: LiveData<List<Country>> = MutableLiveData<List<Country>>(mutableListOf())

    // Поисковый запрос пользователей
    private val _query = MutableLiveData<String>()
    private val query: MutableLiveData<String> = _query

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: MutableLiveData<Boolean> = _isLoading

    private val _messageError = MutableLiveData<String>()
    val messageError: MutableLiveData<String> = _messageError

    init {
        val countryDao = App.component.provideCountryDao()
        repository = CountryRepository(countryDao)
        countries = repository.countries
    }

    /**
     * Вставка данных в [CountryRepository]
     *
     * @param list коллекция [Country]
     */
    private fun insert(list: List<Country>) =
        viewModelScope.launch(Dispatchers.IO) { repository.insert(list) }

    /**
     * Получить список стран соответствующих поисковому запросу query
     *
     * @return список стран удовлетворяющих запрос query
     */
    fun getCountriesByQuery(): LiveData<List<Country>> {
        // MediatorLiveData объединяет два источника LiveData
        val result = MediatorLiveData<List<Country>>()

        // Функция фильтрации стран в соответствии с запросом query
        val filterF = {
            val queryStr = query.value
            val countries = countries.value

            result.value = if (queryStr.isNullOrEmpty()) countries
            else countries?.filter { it.name.contains(queryStr, true) }
        }
        // Объединение LiveData в MediatorLiveData
        result.addSource(countries) { filterF.invoke() }
        result.addSource(query) { filterF.invoke() }

        return result
    }

    /**
     * Загрузить данные из удалённого репозитория
     *
     */
    @SuppressLint("CheckResult")
    fun load() {
        ApiUtils.apiService!!
            .getAllCountries()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe { isLoading.postValue(true) }
            .doFinally { isLoading.postValue(false) }
            .subscribe({
                insert(it)
                messageError.postValue(null)
            }, {
                messageError.postValue("Ошибка подключения")
            })
    }

    /**
     * Обновить запрос
     *
     * @param query текст запроса
     */
    fun handleSearchQuery(query: String?) {
        this.query.value = query
    }
}