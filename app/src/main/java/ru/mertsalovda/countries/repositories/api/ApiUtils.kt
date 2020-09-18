package ru.mertsalovda.countries.repositories.api

import com.google.gson.Gson
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import ru.mertsalovda.countries.BuildConfig

/**
 * Класс для доступа к API https://restcountries.eu/rest/v2/
 */
object ApiUtils {
    var apiService: CountriesApi? = null
    private var gson: Gson = Gson()
    private var okHttpClient: OkHttpClient? = null
    private var retrofit: Retrofit

    init {
        retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.SERVER_URL)
            .client(getClient())
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
        apiService = retrofit.create<CountriesApi>(CountriesApi::class.java)
    }

    /**
     * Подготовка объекта [OkHttpClient]
     *
     * @return объект [OkHttpClient], если сборка DEBUG, то будет добавлен интерсептер логирущий
     * body запроса
     */
    private fun getClient(): OkHttpClient? {
        if (okHttpClient == null) {
            val builder = OkHttpClient.Builder()
            if (BuildConfig.DEBUG){
                builder.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            }
            okHttpClient = builder.build()
        }
        return okHttpClient
    }

}