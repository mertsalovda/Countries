package ru.mertsalovda.countries.ui.detail

import android.content.Context
import android.content.Intent
import android.graphics.drawable.PictureDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_detail.*
import ru.mertsalovda.countries.R
import ru.mertsalovda.countries.extensions.printInColumn
import ru.mertsalovda.countries.repositories.api.ApiUtils
import ru.mertsalovda.countries.utils.glide.GlideApp
import ru.mertsalovda.countries.utils.glide.SvgSoftwareLayerSetter

class DetailActivity : AppCompatActivity() {

    companion object {

        private const val COUNTRY_NAME = "COUNTRY_NAME"

        fun start(context: Context, countryName: String) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(COUNTRY_NAME, countryName)
            context.startActivity(intent)
        }
    }
    private lateinit var countryName: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        countryName = intent.getStringExtra(COUNTRY_NAME) ?: ""

        initToolbar()
        initView()
        initViewModel()
    }

    private fun initToolbar() {
        setSupportActionBar(toolbar)
        supportActionBar?.title = countryName
    }

    private fun initView() {

        ApiUtils.apiService!!
            .getCountryByName(countryName)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                val country = it.first { c -> c.name == countryName }

                tv_name.text = country.name
                tv_language.text = country.languages.printInColumn()
                tv_currency.text = country.currencies.printInColumn()
                tv_timezone.text = country.timezones.printInColumn()

                GlideApp.with(applicationContext)
                    .`as`(PictureDrawable::class.java)
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .error(R.drawable.ic_baseline_error_24)
                    .load(country.flag)
                    .listener(SvgSoftwareLayerSetter())
                    .into(iv_flag)
            }, {})
    }

    private fun initViewModel() {
        // TODO("Not yet implemented")
    }
}