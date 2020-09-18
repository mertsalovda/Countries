package ru.mertsalovda.countries.repositories.room

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import ru.mertsalovda.countries.models.data.Country
import ru.mertsalovda.countries.repositories.room.typeconverters.CurrenciesConverter
import ru.mertsalovda.countries.repositories.room.typeconverters.LanguageConverter
import ru.mertsalovda.countries.repositories.room.typeconverters.TimezoneConverter

@TypeConverters(CurrenciesConverter::class, LanguageConverter::class, TimezoneConverter::class)
@Database(entities = [Country::class], version = 1)
abstract class AppDataBase : RoomDatabase() {
    abstract fun countryDao(): CountryDao
}