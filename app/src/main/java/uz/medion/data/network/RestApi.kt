package uz.medion.data.network

import io.reactivex.Observable
import retrofit2.http.*
import uz.medion.data.model.DoctorCertificateResponse
import uz.medion.data.model.DoctorResponse
import uz.medion.data.model.doctor.*


interface RestApi {

    @POST("/api/v1/doctor")
    fun doctorInfo(
        @Body doctorsControllerPostBody: DoctorsControllerPostBody,
    ): Observable<DoctorControllerPostResponses>


    @GET("/api/v1/doctor/{role}")
    fun getDoctorInfo(
        @Path("role") role: String,
        @Header("Authorization") token: String,
    ): Observable<DoctorGetResponse>

    @Headers("Content-Type: application/json")
    @GET("/api/v1/doctor/mypacientes")
    fun myPatient(
        @Query("username") username: String,
        @Header("Authorization") token: String,
    ): Observable<List<DoctorMyPacientesResponseItem>>


    @PUT("/api/v1/doctor/{id}")
    fun putDoctorInfo(
        @Path("id") id: Int,
        @Body doctorInfoRequest: DoctorPutBody,
    ): Observable<DoctorPutResponses>


    @POST("/api/v1/doctor/add/speciality")
    fun doctorSpeciality(
        @Body specialityBody: DoctorSpecialityBody,
    )

    @POST("/api/v1/doctor/add/sub-speciality")
    fun doctorSubSpeciality(
        @Body addSubSpecialityForDoctorRequest: DoctorSubSpecialityRequest,
    )

    @GET("/api/v1/doctor/certificate")
    fun getCertificates(
        @Query("username") username: String,
    ): Observable<List<DoctorCertificateResponse>>

    @POST("/api/v1/doctor/certificate")
/////////
    fun postCertificate(
    )

//    @Headers("Content-Type: application/json")
//    @GET("/api/v1/doctor/get/{id}")
//
//    fun getDoctorId(
//        @Header("Authorization") token: String,
//        @Path("id") id: Int
//
//    ): Observable<DoctorGetResponse>

    @GET("/api/v1/doctor/getfavorites")
    fun getFavourite(
        @Query("name") name: String,
        @Header("Authorization") token: String,
    ): Observable<List<DoctorMyPacientesResponseItem>>

    @POST("/api/v1/doctor/favorite/{clientId}")
    fun setFavouritePatient(

        @Path("clientId") clientId: Int,
        @Header("Authorization") token: String,
    ): Observable<Boolean>

    @GET("")
    fun patientById(
        @Path("id") id: Int,
        @Header("Authorization") token: String,
    ): Observable<DoctorResponse>
//    @POST("/api/v1/doctor/{id}")
//    suspend fun addDoctorInfo(@FieldMap docMap: HashMap<String,String>):Response<DoctorResponse>
}