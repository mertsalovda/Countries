package ru.mertsalovda.countries.ui.detail

import android.annotation.SuppressLint
import androidx.lifecycle.*
import ru.mertsalovda.countries.App
import ru.mertsalovda.countries.models.data.Country
import ru.mertsalovda.countries.repositories.CountryRepository

class DetailViewModel : ViewModel() {

    private val repository: CountryRepository

    private val countries: LiveData<List<Country>>
    private val countryId = MutableLiveData<String>("")

    init {
        val countryDao = App.component.provideCountryDao()
        repository = CountryRepository(countryDao)
        countries = repository.countries
    }

    /**
     * Получить страну по ID
     *
     */
    fun getCountry(): LiveData<Country> {
        // MediatorLiveData объединяет два источника LiveData
        val result = MediatorLiveData<Country>()

        // Функция фильтрации в соответствии с countryId
        val filterF = {
            val queryName = this.countryId.value!!
            val countryList = countries.value!!

            result.value = if (queryName.isEmpty()) countries.value!!.first()
            else countryList.first { it.name == queryName }
        }
        // Объединение LiveData в MediatorLiveData
        result.addSource(countries) { filterF.invoke() }
        result.addSource(this.countryId) { filterF.invoke() }

        return result
    }

    /**
     * Загрузить данные о стране из репозитория
     *
     * @param name
     */
    @SuppressLint("CheckResult")
    fun load(name: String) {
        countryId.postValue(name)
    }
}