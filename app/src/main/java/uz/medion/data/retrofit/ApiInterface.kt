package uz.medion.data.retrofit

import io.reactivex.Observable
import retrofit2.http.*
import uz.medion.data.model.*
import uz.medion.data.model.doctor.DoctorGetResponse

interface ApiInterface {

    @GET("/api/v1/registration")
    fun isRegistrationFlowAvailable(
        @Header("username") userName: String,
    ): Observable<IsRegistrationFlowAvailable>

    @POST("/api/v1/registration/request") // createRegistrationRequest
    fun requestMail(
        @Body phoneNumber: RegistrationCreateRequest, // phone number
    ): Observable<ResponseOfRequestEmail>

    @Headers("Content-Type: application/json")
    @POST("/api/v1/registration/{requestId}")
    fun verifyAndRegisterUser(
        @Path("requestId") requestId: String,
        @Query("code") code: String,
        @Body registrationRequest: RegistrationRequest,
    ): Observable<RegistrationResponse>

    @Headers("Content-Type: application/json")
    @POST("/api/v1/auth/login")
    fun login(
        @Body login: Login,
    ): Observable<UserLogin>

    //HomeFragment:: About clinic
    @Headers("Content-Type: application/json")
    @GET("/api/v1/about/info")
    fun aboutClinic(
        @Header("Authorization") token: String,
    ): Observable<AboutClinicResponse>

    //HomeFragment:: Specialities
    @Headers("Content-Type: application/json")
    @GET("/api/v1/speciality/list")
    fun speciality(
        @Header("Authorization") token: String,
    ): Observable<List<SpecialityItemResponse>>

    //OurDoctorsFragment:: Getting Doctors by Speciality
    @Headers("Content-Type: application/json")
    @GET
    fun doctorsBySpeciality(
        @Url url: String,
        @Header("Authorization") token: String,
    ): Observable<List<DoctorResponse>>

    //Getting subSpeciality
    @Headers("Content-Type: application/json")
    @GET("/api/v1/sub-speciality/{specialityId}")
    fun subSpeciality(
        @Path("specialityId") specialityId: Int,
        @Header("Authorization") token: String
    ): Observable<List<SubSpecialityResponse>>

    //Filtering doctors
    @Headers("Content-Type: application/json")
    @GET("/api/v1/booking/check")
    fun filterDoctors(
        @Query("date")date: String,
        @Query("specialityId") specialityId: Int,
        @Query("subSpecialityId") subSpecialityId: Int,
        @Header("Authorization") token: String
    ): Observable<List<DoctorResponse>>

    //AboutDoctor:: Booking
    @GET("/api/v1/doctor/get/{id}")
    fun  doctorById(
        @Path("id") id: Int,
        @Header("Authorization") token: String
    ): Observable<DoctorResponse>

    @Headers("Content-Type: application/json")
    @GET("/api/v1/booking/date")
    fun monthlyDate(
        @Query("doctorId") doctorId: Int,
        @Header("Authorization") token: String,
    ): Observable<List<MonthlyDateResponse>>

    @Headers("Content-Type: application/json")
    @GET("/api/v1/booking/time")
    fun monthlyTime(
        @Query("date") date: String,
        @Query("doctorId") doctorId: Int,
        @Header("Authorization") token: String,
    ): Observable<List<MonthlyTimeResponse>>

    @Headers("Content-Type: application/json")
    @GET("/api/v1/booking/allBookings")
    fun bookedEvent(
        @Query("username") userName: String,
        @Header("Authorization") token: String
    ): Observable<List<BookingResponse>>

    //Address and contacts
    @Headers("Content-Type: application/json")
    @GET("/api/v1/branch")
    fun branch(
        @Header("Authorization") token: String
    ): Observable<List<BranchResponse>>

    //About Doctor // comments
    @Headers("Content-Type: application/json")
    @GET("/api/v1/comment/{doctorId}")
    fun comments(
        @Path("doctorId") doctorId: Int,
        @Header("Authorization") token: String,
    ): Observable<List<CommentResponse>>

    @Headers("Content-Type: application/json")
    @POST("/api/v1/comment")
    fun sendComment(
        @Body comment: SendComment,
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
    fun myDoctors(
        @Query("name") userName: String,
        @Header("Authorization") token: String,
    ): Observable<List<DoctorResponse>>

    @Headers("Content-Type: application/json")
    @GET("/api/v1/user/getfavorite")
    fun myDoctorsFavourite(
        @Query("name") userName: String,
        @Header("Authorization") token: String,
    ): Observable<List<DoctorResponse>>

    @Headers("Content-Type: application/json")
    @POST("/api/v1/user/favorite/{doctorId}")
    fun setDoctorFavourite(
        @Path("doctorId") doctorId: Int,
        @Header("Authorization") token: String
    ): Observable<Boolean>


















    @Headers("Content-Type: application/json")
    @GET("/api/v1/doctor/get/{id}")

    fun getDoctorId(
        @Header("Authorization") token: String,
        @Path("id") id: Int

    ): Observable<DoctorGetResponse>


}