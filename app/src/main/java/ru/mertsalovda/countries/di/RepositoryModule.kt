package ru.mertsalovda.countries.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import ru.mertsalovda.countries.repositories.room.AppDataBase
import ru.mertsalovda.countries.repositories.room.CountryDao
import javax.inject.Singleton

@Module
class RepositoryModule(private val context: Context) {

    @Singleton
    @Provides
    fun provideAddDataBase(): CountryDao {
        return Room.databaseBuilder(context, AppDataBase::class.java, "database").build().countryDao()
    }
}