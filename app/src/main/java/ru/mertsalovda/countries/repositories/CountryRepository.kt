package ru.mertsalovda.countries.repositories

import ru.mertsalovda.countries.models.data.Country
import ru.mertsalovda.countries.repositories.room.CountryDao

class CountryRepository(private val countryDao: CountryDao) {

    val countries = countryDao.getAllCountries()

    suspend fun insert(list: List<Country>) {
        countryDao.insert(list)
    }
}