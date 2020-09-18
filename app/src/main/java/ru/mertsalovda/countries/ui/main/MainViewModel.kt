package ru.mertsalovda.countries.ui.main

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

    init {
        val countryDao = App.component.provideCountryDao()
        repository = CountryRepository(countryDao)
        countries = repository.countries
    }

    private fun insert(list: List<Country>)
            = viewModelScope.launch(Dispatchers.IO) { repository.insert(list) }

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: MutableLiveData<Boolean> = _isLoading

    /**
     * Загрузить данные из репозитория
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
            .subscribe({ insert(it) }, {
                it.printStackTrace()
            })
    }
}