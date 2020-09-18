package ru.mertsalovda.countries

import android.app.Application
import ru.mertsalovda.countries.di.AppComponent
import ru.mertsalovda.countries.di.DaggerAppComponent
import ru.mertsalovda.countries.di.RepositoryModule

class App : Application() {
    companion object {
        lateinit var component: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        
        component = DaggerAppComponent.builder().repositoryModule(RepositoryModule(this)).build()
    }
}