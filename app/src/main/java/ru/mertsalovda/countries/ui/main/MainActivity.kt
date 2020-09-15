package ru.mertsalovda.countries.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import ru.mertsalovda.countries.R
import ru.mertsalovda.countries.models.data.Country

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: CountriesAdapter

    private val countryList = listOf<Country>(
        Country("Russia", "https://restcountries.eu/data/rus.svg", listOf(), listOf(), listOf()),
        Country("Russia", "https://restcountries.eu/data/rus.svg", listOf(), listOf(), listOf()),
        Country("Russia", "https://restcountries.eu/data/rus.svg", listOf(), listOf(), listOf()),
        Country("Russia", "https://restcountries.eu/data/rus.svg", listOf(), listOf(), listOf()),
        Country("Russia", "https://restcountries.eu/data/rus.svg", listOf(), listOf(), listOf()),
        Country("Russia", "https://restcountries.eu/data/rus.svg", listOf(), listOf(), listOf()),
        Country("Russia", "https://restcountries.eu/data/rus.svg", listOf(), listOf(), listOf()),
        Country("Russia", "https://restcountries.eu/data/rus.svg", listOf(), listOf(), listOf()),
        Country("Russia", "https://restcountries.eu/data/rus.svg", listOf(), listOf(), listOf()),
        Country("Russia", "https://restcountries.eu/data/rus.svg", listOf(), listOf(), listOf()),
        Country("Russia", "https://restcountries.eu/data/rus.svg", listOf(), listOf(), listOf()),
        Country("Russia", "https://restcountries.eu/data/rus.svg", listOf(), listOf(), listOf()),
        Country("Russia", "https://restcountries.eu/data/rus.svg", listOf(), listOf(), listOf()),
        Country("Russia", "https://restcountries.eu/data/rus.svg", listOf(), listOf(), listOf()),
        Country("Russia", "https://restcountries.eu/data/rus.svg", listOf(), listOf(), listOf()),
        Country("Russia", "https://restcountries.eu/data/rus.svg", listOf(), listOf(), listOf())
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()
        initView()
        initViewModel()
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Страны"
    }

    private fun initView() {
        adapter = CountriesAdapter {
            Snackbar.make(
                rv_country_list,
                "Click on ${it.name}",
                Snackbar.LENGTH_LONG
            ).show()
        }
        val layoutManager = LinearLayoutManager(this)

        rv_country_list.also {
            it.adapter = adapter
            it.layoutManager = layoutManager
        }
        adapter.updateData(countryList)
    }

    private fun initViewModel() {
        // TODO("Not yet implemented")
    }
}