package uz.medion.data.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import uz.medion.data.constants.Constants
import java.util.concurrent.TimeUnit

class CreateRetrofit {
    companion object {
        private var retrofit: Retrofit? = null
        var api: RestApi? = null


        fun restartRetrofit() {
            retrofit = null
        }



    fun getRetrofit(): RestApi {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val client = OkHttpClient.Builder()
            .connectTimeout(100, TimeUnit.SECONDS)
            .readTimeout(100, TimeUnit.SECONDS)
            .addInterceptor { chain ->
                val request = chain.request().newBuilder().apply {
                    addHeader("Content-Type", "application/json")
                }.build()
                chain.proceed(request)
            }.addInterceptor(loggingInterceptor)
            .build()
        if (retrofit == null) {


            retrofit = Retrofit.Builder()
                .baseUrl(Constants.BASE_API_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
            api = retrofit!!.create(RestApi::class.java)
        }
        return api!!

    }
}}