package uz.medion.data.network

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query
import uz.medion.data.model.doctor.DoctorInfo

const val BASE_URL = "http://45.137.148.124:8081/api/v1/swagger-ui/#/"

object Network {
    val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}

interface RestApi {
    @GET("doctors-controller/getAllSpecialitiesUsingGET")
    suspend fun getDoctor(@Query("id") id: Int): Response<DoctorInfo>
}