package ru.mertsalovda.countries.ui.detail

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import ru.mertsalovda.countries.models.data.Country
import ru.mertsalovda.countries.repositories.api.ApiUtils

class DetailViewModel : ViewModel() {

    private val _country = MutableLiveData<Country>()
    val country = _country

    /**
     * Загрузить данные о стране из репозитория
     *
     * @param name
     */
    @SuppressLint("CheckResult")
    fun load(name: String){
        ApiUtils.apiService!!
            .getCountryByName(name)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                country.postValue(it.first { c -> c.name == name })
            }, {})
    }
}