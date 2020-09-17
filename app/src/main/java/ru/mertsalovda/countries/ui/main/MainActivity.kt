package ru.mertsalovda.countries.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*
import ru.mertsalovda.countries.R
import ru.mertsalovda.countries.repositories.api.ApiUtils
import ru.mertsalovda.countries.ui.detail.DetailActivity

class MainActivity : AppCompatActivity() {

    private lateinit var adapter: CountriesAdapter

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
        adapter = CountriesAdapter { DetailActivity.start(this, it.name) }
        val layoutManager = LinearLayoutManager(this)

        rv_country_list.also {
            it.adapter = adapter
            it.layoutManager = layoutManager
        }

        ApiUtils.apiService!!
            .getAllCountries()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                adapter.updateData(it)
            }, {})
    }

    private fun initViewModel() {
        // TODO("Not yet implemented")
    }
}