package ru.mertsalovda.countries.repositories.api

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.mertsalovda.countries.BuildConfig
import java.io.IOException
import java.lang.Exception
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException


object ApiUtils {
    private var gson: Gson? = null
    var apiService: CountriesApi? = null
    private var okHttpClient: OkHttpClient? = null
    private var retrofit: Retrofit? = null


    val NETWORK_EXCEPTIONS = arrayListOf(
        UnknownHostException::class.java,
        SocketTimeoutException::class.java,
        ConnectException::class.java
    )

    init {
        gson = Gson()
        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .client(getClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        apiService = retrofit!!.create<CountriesApi>(CountriesApi::class.java)
    }

    private fun getClient(): OkHttpClient? {
        if (okHttpClient == null) {
            val builder = OkHttpClient.Builder()
            builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            okHttpClient = builder.build()
        }
        return okHttpClient
    }

}