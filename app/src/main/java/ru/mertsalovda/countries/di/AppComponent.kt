package ru.mertsalovda.countries.di

import dagger.Component
import ru.mertsalovda.countries.repositories.room.CountryDao
import javax.inject.Singleton

@Singleton
@Component(modules = [RepositoryModule::class])
interface AppComponent {
    fun provideCountryDao(): CountryDao
}