package uz.medion.data.retrofit.auth

import io.reactivex.Observable
import retrofit2.http.*
import uz.medion.data.model.*

interface AuthApi {

    @GET("/api/v1/registration")
    fun isRegistrationFlowAvailable(
        @Header("username") userName: String,
    ): Observable<IsRegistrationFlowAvailable>

    @POST("/api/v1/registration/request") // createRegistrationRequest
    fun requestMail(
        @Body phoneNumber: RegistrationCreateRequest, // phone number
    ): Observable<EmailResponseResponse>

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

}