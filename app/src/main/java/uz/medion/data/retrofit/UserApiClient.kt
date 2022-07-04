package uz.medion.data.retrofit.user

import okhttp3.OkHttpClient
import okhttp3.RequestBody
import okhttp3.logging.HttpLoggingInterceptor
import okio.Buffer
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import uz.medion.data.constants.Constants
import java.io.IOException
import java.util.concurrent.TimeUnit


class UserApiClient {
    companion object {
        private var retrofit: Retrofit? = null
        var userApi: UserApi? = null

        fun restartRetrofit() {
            retrofit = null
        }

        fun getApiClient(): UserApi {

            val logging = HttpLoggingInterceptor()
            logging.level = HttpLoggingInterceptor.Level.BODY

            val okHttpClient = OkHttpClient.Builder()
                .readTimeout(100, TimeUnit.SECONDS)
                .connectTimeout(100, TimeUnit.SECONDS)
                .addInterceptor { chain ->
                    val request = chain.request().newBuilder().apply {
                        addHeader("Content-Type", "application/json")
                    }.build()
                    chain.proceed(request)
                }.addInterceptor(logging)
                .build()
            if (retrofit == null) {
                retrofit = Retrofit.Builder()
                    .baseUrl(Constants.BASE_API_URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                userApi = retrofit!!.create(UserApi::class.java)
            }
            return userApi!!
        }

        private fun bodyToString(request: RequestBody): String? {
            return try {
                val buffer = Buffer()
                request.writeTo(buffer)
                buffer.readUtf8()
            } catch (e: IOException) {
                "did not work"
            }
        }
    }
}