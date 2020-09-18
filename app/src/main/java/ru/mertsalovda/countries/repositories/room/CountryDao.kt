package ru.mertsalovda.countries.repositories.room

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.mertsalovda.countries.models.data.Country

@Dao
interface CountryDao {
    /**
     * Добавить страны в базу данных. Совпадения будут заменены.
     *
     * @param countries коллекция стран [Country]
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(countries: List<Country>)

    /**
     * Получить список всех стран из БД.
     *
     * @return [LiveData] cо списком стран [Country]
     */
    @Query("SELECT * FROM country")
    fun getAllCountries(): LiveData<List<Country>>
}