package ru.mertsalovda.countries.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.error_layout.*
import ru.mertsalovda.countries.R
import ru.mertsalovda.countries.ui.detail.DetailActivity
import ru.mertsalovda.countries.viewmodels.MainViewModel

class MainActivity : AppCompatActivity(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var adapter: CountriesAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initToolbar()
        initViewModel()
        initView()
    }

    /**
     * Настройка Toolbar
     *
     */
    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Страны"
    }

    /**
     * Инициализировать view-компоненты и настроить слушателей
     *
     */
    private fun initView() {
        adapter = CountriesAdapter { DetailActivity.start(this, it.name) }
        val layoutManager = LinearLayoutManager(this)

        rv_country_list.also {
            it.adapter = adapter
            it.layoutManager = layoutManager
        }

        refresher.setOnRefreshListener(this)

        viewModel.isLoading.observe(this, Observer { refresher.isRefreshing = it })
        viewModel.countries.observe(this, Observer {
            if (it.isNullOrEmpty()) {
                showError()
            } else {
                hideError()
                adapter.updateData(it)
            }
        })
        viewModel.messageError.observe(this, Observer {
            if (!it.isNullOrEmpty()) {
                Snackbar.make(rv_country_list, it, Snackbar.LENGTH_LONG)
                    .setAction("Обновить") { onRefresh() }
                    .show()
            }
        })
        if (adapter.itemCount == 0) {
            onRefresh()
        }
    }

    /**
     * Инициализировать MainViewModel и запустить обновление списка
     *
     */
    private fun initViewModel() {
        viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)
    }

    override fun onRefresh() {
        viewModel.load()
    }

    /**
     * Показать заглушку и скрыть список
     *
     */
    private fun showError() {
        rv_country_list.visibility = View.GONE
        iv_error.visibility = View.VISIBLE
        tv_error.visibility = View.VISIBLE
    }

    /**
     * Скрыть заглушку и показать список
     *
     */
    private fun hideError() {
        rv_country_list.visibility = View.VISIBLE
        iv_error.visibility = View.GONE
        tv_error.visibility = View.GONE
    }
}