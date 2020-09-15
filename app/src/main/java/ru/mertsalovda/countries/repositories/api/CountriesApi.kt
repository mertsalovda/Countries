package ru.mertsalovda.countries.repositories.api

import io.reactivex.Observable
import retrofit2.http.GET
import ru.mertsalovda.countries.models.data.Country

interface CountriesApi {

    @GET("all?fields=name;flag;currencies;languages;timezones")
    fun getAllCountries() : Observable<List<Country>>

    @GET("name/rico?fields=name;flag;currencies;languages;timezones")
    fun getPuertoRico() : Observable<List<Country>>
}