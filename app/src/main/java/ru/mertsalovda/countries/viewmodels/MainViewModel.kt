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

    val countries: LiveData<List<Country>>

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
}