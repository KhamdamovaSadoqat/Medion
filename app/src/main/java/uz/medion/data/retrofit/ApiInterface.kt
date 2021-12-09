package uz.medion.data.retrofit

import io.reactivex.Observable
import retrofit2.http.*
import uz.medion.data.model.*

interface ApiInterface {

    @FormUrlEncoded
    @POST("HERE SHOULD BE URL")
    fun sendComment(
        @Field("SOME FIELD") id: Int,
        @Field("SOME FIELS") answer: String,
        @Header("Authorization") token: String
    ): Observable<SUCCESSTEST>

    @GET("/api/v1/registration")
    fun isRegistrationFlowAvailable(
        @Header("username") userName: String
    ): Observable<IsRegistrationFlowAvailable>

    @FormUrlEncoded
    @POST("/api/v1/registration/request")
    fun requestMail(
        @Query("username") userName: String, // email
        @Field("email") email: String
    ): Observable<ResponseOfRequestEmail>


    @FormUrlEncoded
    @POST("/api/v1/auth/login")
    fun login(
        @Field("password") password: String,
        @Field("username") userName: String
    ): Observable<UserLogin>

    @Headers("Content-Type: application/json")
    @POST("/api/v1/registration/{requestId}")
    fun verifyAndRegisterUser(
        @Path("requestId") requestId: String,
        @Query("code") code: String,
        @Body registrationRequest: RegistrationRequest
    ): Observable<RegistrationResponse>

}