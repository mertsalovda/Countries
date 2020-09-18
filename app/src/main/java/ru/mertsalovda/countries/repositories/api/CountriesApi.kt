package ru.mertsalovda.countries.repositories.api

import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import ru.mertsalovda.countries.models.data.Country

interface CountriesApi {

    @GET("all?fields=name;flag;currencies;languages;timezones")
    fun getAllCountries() : Single<List<Country>>

    @GET("name/{name}?fullText=true;flag;currencies;languages;timezones")
    fun getCountryByName(@Path("name") name: String) : Observable<List<Country>>
}