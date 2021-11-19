package uz.medion.data.retrofit

import io.reactivex.Observable
import retrofit2.http.*
import uz.medion.data.model.IsRegistrationFlowAvailable
import uz.medion.data.model.ResponseOfRequestEmail
import uz.medion.data.model.SUCCESSTEST

interface ApiInterface {

    //Get
    //post

//    @GET
//    fun getHuquqiyBilimlarSinovi(
//        @Url url: String,
//        @Header("Authorization") token: String
//    ): Observable<HuquqiyBilimlarSinovi>

    @FormUrlEncoded
    @POST("HERE SHOULD BE URL")
    fun sendComment(
        @Field("SOME FIELD") id: Int,
        @Field("SOME FIELS") answer: String,
        @Header("Authorization") token: String
    ): Observable<SUCCESSTEST>

    @GET("http://45.137.148.124:8080/api/v1/registration/admin@admin.com")
    fun isRegistrationFlowAvailable(
        @Header("username") userName: String
    ): Observable<IsRegistrationFlowAvailable>

    @FormUrlEncoded
    @POST("http://45.137.148.124:8080/api/v1/registration/request")
    fun requestMail(
        @Field("email")email: String
    ): Observable<ResponseOfRequestEmail>
}