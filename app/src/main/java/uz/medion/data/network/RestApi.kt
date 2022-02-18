package uz.medion.data.network

import io.reactivex.Observable
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import uz.medion.data.model.doctor.DoctorControllerPostResponses
import uz.medion.data.model.doctor.DoctorGetResponse
import uz.medion.data.model.doctor.DoctorMyPacientesResponseItem
import uz.medion.data.model.doctor.DoctorsControllerPostBody


interface RestApi {

    @POST("/api/v1/doctor")
    fun doctorInfo(
        @Body doctorsControllerPostBody: DoctorsControllerPostBody,
    ): Observable<DoctorControllerPostResponses>


    @GET("/api/v1/doctor")
    fun getDoctorInfo(@Header("role") role: String): Observable<DoctorGetResponse>

    @GET("/api/v1/doctor/mypacientes")
    fun getPatient(@Header("username") username:String):Observable<DoctorMyPacientesResponseItem>


//    @POST("/api/v1/doctor/{id}")
//    suspend fun addDoctorInfo(@FieldMap docMap: HashMap<String,String>):Response<DoctorResponse>
}