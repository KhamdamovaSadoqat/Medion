package uz.medion.data.retrofit.user

import io.reactivex.Observable
import retrofit2.http.*
import uz.medion.data.model.*

interface UserApi {

    //HomeFragment:: About clinic
    @Headers("Content-Type: application/json")
    @GET("/api/v1/about/info")
    fun getAboutClinic(
        @Header("Authorization") token: String,
    ): Observable<AboutClinicResponse>

    //HomeFragment:: Specialities
    @Headers("Content-Type: application/json")
    @GET("/api/v1/speciality/list")
    fun getSpeciality(
        @Header("Authorization") token: String,
    ): Observable<List<SpecialityItemResponse>>

    //OurDoctorsFragment:: Getting Doctors by Speciality
    @Headers("Content-Type: application/json")
    @GET
    fun getDoctorsBySpeciality(
        @Url url: String,
        @Header("Authorization") token: String,
    ): Observable<List<DoctorResponse>>

    //Getting subSpeciality
    @Headers("Content-Type: application/json")
    @GET("/api/v1/sub-speciality/{specialityId}")
    fun getSubSpeciality(
        @Path("specialityId") specialityId: Int,
        @Header("Authorization") token: String
    ): Observable<List<SubSpecialityResponse>>

    //Filtering doctors
    @Headers("Content-Type: application/json")
    @GET("/api/v1/booking/check")
    fun getFilteredDoctors(
        @Query("date")date: String,
        @Query("specialityId") specialityId: Int,
        @Query("subSpecialityId") subSpecialityId: Int,
        @Header("Authorization") token: String
    ): Observable<List<DoctorResponse>>

    //AboutDoctor:: Booking
    @GET("/api/v1/doctor/get/{id}")
    fun getDoctorById(
        @Path("id") id: Int,
        @Header("Authorization") token: String
    ): Observable<DoctorResponse>

    @Headers("Content-Type: application/json")
    @GET("/api/v1/booking/date")
    fun getMonthlyDate(
        @Query("doctorId") doctorId: Int,
        @Header("Authorization") token: String,
    ): Observable<List<MonthlyDateResponse>>

    @Headers("Content-Type: application/json")
    @GET("/api/v1/booking/time")
    fun getMonthlyTime(
        @Query("date") date: String,
        @Query("doctorId") doctorId: Int,
        @Header("Authorization") token: String,
    ): Observable<List<MonthlyTimeResponse>>

    @Headers("Content-Type: application/json")
    @GET("/api/v1/booking/allBookings")
    fun getBookedEvent(
        @Query("username") userName: String,
        @Header("Authorization") token: String
    ): Observable<List<BookingResponse>>

    //Address and contacts
    @Headers("Content-Type: application/json")
    @GET("/api/v1/branch")
    fun getBranch(
        @Header("Authorization") token: String
    ): Observable<List<BranchResponse>>

    //About Doctor // comments
    @Headers("Content-Type: application/json")
    @GET("/api/v1/comment/{doctorId}")
    fun getComments(
        @Path("doctorId") doctorId: Int,
        @Header("Authorization") token: String,
    ): Observable<List<CommentResponse>>

    @Headers("Content-Type: application/json")
    @POST("/api/v1/comment")
    fun postComment(
        @Body commentRequest: CommentRequest,
        @Header("Authorization") token: String,
    ): Observable<List<CommentResponse>>

    //About Doctor // certificates
    @Headers("Content-Type: application/json")
    @GET("/api/v1/doctor/certificate")
    fun getCertificate(
        @Query("name") userName: String,
        @Header("Authorization") token: String,
    ): Observable<List<DoctorCertificateResponse>>

    //MyDoctor //favourites
    @Headers("Content-Type: application/json")
    @GET("/api/v1/user/mydoctors")
    fun getMyDoctors(
        @Query("name") userName: String,
        @Header("Authorization") token: String,
    ): Observable<List<DoctorResponse>>

    @Headers("Content-Type: application/json")
    @GET("/api/v1/user/getfavorite")
    fun getMyDoctorsFavourite(
        @Query("name") userName: String,
        @Header("Authorization") token: String,
    ): Observable<List<DoctorResponse>>

    @Headers("Content-Type: application/json")
    @POST("/api/v1/user/favorite/{doctorId}")
    fun postDoctorFavourite(
        @Path("doctorId") doctorId: Int,
        @Header("Authorization") token: String
    ): Observable<Boolean>

    @Headers("Content-Type: application/json")
    @GET("/api/v1/estetic/parent")
    fun getEsteticMedicine(
        @Header("Authorization") token: String,
    ): Observable<List<EsteticMedicineResponse>>

    @Headers("Content-Type: application/json")
    @POST("api/v1/chat")
    fun postChat(
        @Header("Authorization") token: String,
        @Body message: MessageRequest,
    ): Observable<Boolean>

}