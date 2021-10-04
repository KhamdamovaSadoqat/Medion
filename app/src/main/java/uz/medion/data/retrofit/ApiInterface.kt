package uz.medion.data.retrofit

import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.Header
import retrofit2.http.POST
import uz.medion.data.model.SUCCESSTEST

interface ApiInterface {

    //Get
    //post

    @FormUrlEncoded
    @POST("HERE SHOULD BE URL")
    fun sendComment(
        @Field("SOME FIELD") id: Int,
        @Field("SOME FIELS") answer: String, @Header("Authorization") token: String
    ): Observable<SUCCESSTEST>


}