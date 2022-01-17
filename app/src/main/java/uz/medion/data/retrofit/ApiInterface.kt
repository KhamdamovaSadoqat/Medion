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

    @POST("/api/v1/registration/request") // createRegistrationRequest
    fun requestMail(
        @Query("username") userName: String // email
    ): Observable<ResponseOfRequestEmail>

    @Headers("Content-Type: application/json")
    @POST("/api/v1/registration/{requestId}")
    fun verifyAndRegisterUser(
        @Path("requestId") requestId: String,
        @Query("code") code: String,
        @Body registrationRequest: RegistrationRequest
    ): Observable<RegistrationResponse>

    @Headers("Content-Type: application/json")
    @POST("/api/v1/auth/login")
    fun login(
        @Body login: Login
    ): Observable<UserLogin>

    //Address and contacts
    @GET("/api/v1/branch")
    fun branch(
        @Header("Authorization") token: String
    ): Observable<List<BranchResponse>>
}