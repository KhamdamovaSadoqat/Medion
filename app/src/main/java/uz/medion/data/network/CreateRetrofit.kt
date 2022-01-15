package uz.medion.data.network

import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object CreateRetrofit {
    private val logginInterceptor = HttpLoggingInterceptor()
    private var retrofit: Retrofit? = null



    fun getRetrofit(interceptor: Interceptor): Retrofit {
        if (retrofit != null) {
            return retrofit!!
        }
        val client = OkHttpClient.Builder()
            .addInterceptor(logginInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(15, TimeUnit.SECONDS)
            .writeTimeout(15, TimeUnit.SECONDS)
            .build()
        retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit!!

    }
}