package ru.mertsalovda.countries.repositories.api

import io.reactivex.Single
import retrofit2.http.GET
import ru.mertsalovda.countries.models.data.Country

interface CountriesApi {
    /**
     * Запрос на получение списка всех стран.
     * Фильтр NAME, FLAG, CURRENCIES, LANGUAGE, TIMEZONE.
     *
     * @return [Single] оъект со списком всех стран.
     */
    @GET("all?fields=name;flag;currencies;languages;timezones")
    fun getAllCountries() : Single<List<Country>>
}