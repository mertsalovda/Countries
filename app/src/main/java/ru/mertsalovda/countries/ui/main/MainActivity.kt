package ru.mertsalovda.countries.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.error_layout.*
import ru.mertsalovda.countries.R
import ru.mertsalovda.countries.ui.detail.DetailActivity

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

        refresher.setOnRefreshListener(this)

        viewModel.isLoading.observe(this, Observer { refresher.isRefreshing = it })
        viewModel.countries.observe(this, Observer {
            if (it.isEmpty()){
                showError()
            } else {
                hideError()
                adapter.updateData(it)
            }
        })
    }

    private fun initViewModel() {
        viewModel =
            ViewModelProvider.AndroidViewModelFactory(application).create(MainViewModel::class.java)
        onRefresh()
    }

    override fun onRefresh() {
        viewModel.load()
    }

    private fun showError() {
        rv_country_list.visibility = View.GONE
        iv_error.visibility = View.VISIBLE
        tv_error.visibility = View.VISIBLE
    }

    private fun hideError() {
        rv_country_list.visibility = View.VISIBLE
        iv_error.visibility = View.GONE
        tv_error.visibility = View.GONE
    }
}