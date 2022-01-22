package uz.medion.data.network

import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import uz.medion.data.constants.Constants
import uz.medion.data.model.doctor.DocRegistration
import uz.medion.data.model.doctor.DoctorResponse


object Network {
    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(Constants.BASE_API_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

}

interface RestApi {
    @GET("doctors-controller/getAllSpecialitiesUsingGET")
    suspend fun getDoctor(@Query("id") id: Int): Response<DoctorResponse>

    @FormUrlEncoded
    @POST("/api/v1/doctor/{id}")
    suspend fun addDoctorInfo(@FieldMap docMap: HashMap<String,String>):Response<DoctorResponse>
}