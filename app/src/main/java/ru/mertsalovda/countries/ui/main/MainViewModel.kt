package ru.mertsalovda.countries.ui.main

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.mertsalovda.countries.models.data.Country
import ru.mertsalovda.countries.repositories.api.ApiUtils

class MainViewModel : ViewModel() {
    private val _countries = MutableLiveData<List<Country>>()
    val countries: MutableLiveData<List<Country>> = _countries

    private val _isLoading = MutableLiveData<Boolean>(false)
    val isLoading: MutableLiveData<Boolean> = _isLoading

    /**
     * Загрузить данные из репозитория
     *
     */
    @SuppressLint("CheckResult")
    fun load() {
        isLoading.postValue(true)
        ApiUtils.apiService!!
            .getAllCountries()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                countries.postValue(it)
                isLoading.postValue(false)
            }, {
                isLoading.postValue(false)
            })
    }
}