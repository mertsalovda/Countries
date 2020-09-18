package ru.mertsalovda.countries.repositories.room

import androidx.lifecycle.LiveData
import androidx.room.*
import ru.mertsalovda.countries.models.data.Country

@Dao
interface CountryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(countries: List<Country>)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun update(countries: List<Country>)

    @Query("SELECT * FROM country")
    fun getAllCountries(): LiveData<List<Country>>

    @Query("SELECT * FROM country WHERE name = :name")
    fun getCountryById(name: String): LiveData<Country>
}