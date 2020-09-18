package ru.mertsalovda.countries.repositories

import ru.mertsalovda.countries.models.data.Country
import ru.mertsalovda.countries.repositories.room.CountryDao

/**
 * Репозиторий стран [Country]
 *
 * @property countryDao Dao стран
 */
class CountryRepository(private val countryDao: CountryDao) {

    val countries = countryDao.getAllCountries()

    /**
     * Вставить данные в базу данных
     *
     * @param list коллекция [Country] для добавления в базу данных
     */
    suspend fun insert(list: List<Country>) {
        countryDao.insert(list)
    }
}