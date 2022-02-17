package uz.medion.data.repository

import android.annotation.SuppressLint
import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import uz.medion.data.constants.Constants
import uz.medion.data.model.doctor.DoctorControllerPostResponses
import uz.medion.data.model.doctor.DoctorGetResponse
import uz.medion.data.model.doctor.DoctorMyPacientesResponseItem
import uz.medion.data.model.doctor.DoctorsControllerPostBody
import uz.medion.data.model.remote.Resource
import uz.medion.data.model.remote.Status
import uz.medion.data.network.CreateRetrofit
class DocRepository() {
    private val compositeDisposable = CompositeDisposable()
    private val apiClient = CreateRetrofit.getRetrofit()
    private val TAG = "DocRepository"
    private var updateTime: Long = 0





//    @SuppressLint("LogConditional")
//    suspend fun addDoctorInfo(map: HashMap<String, String>): ArrayList<DoctorResponse> {
//        val response = service.addDoctorInfo(map)
//        Log.d(TAG, "addDoctorInfo: ${response.code()}")
//        if (response.isSuccessful && response.body() != null) {
//            return arrayListOf(response.body()!!)
//        } else {
//            Log.e(TAG, "addDoctorInfo: ${response.errorBody()} ")
//        }
//        return arrayListOf()
//
//    }

    fun doctorInfo(
        doctorsControllerPostBody: DoctorsControllerPostBody,
        response:MutableLiveData<Resource<DoctorControllerPostResponses>>
    ){
        compositeDisposable.add(
            apiClient.doctorInfo(
                doctorsControllerPostBody
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableObserver<DoctorControllerPostResponses>(){
                    override fun onNext(t: DoctorControllerPostResponses) {
                        response.value= Resource(Status.SUCCESS,t,null,null)
                    }

                    override fun onError(e: Throwable) {
                        if (e.message?.contains("401",true)==true)
                        {
                            Constants.setUnAuthorized(true)
                        }
                        response.value= Resource(Status.ERROR,null,e.message,e)
                    }

                    override fun onComplete() {}

                })
        )
        response.value= Resource(Status.LOADING,null,null,null)
        Log.d(TAG, "doctorInfo: $doctorsControllerPostBody")
    }

    fun getDoctorInfo(
        role:String,
        response:MutableLiveData<Resource<DoctorGetResponse>>   )
    {
        compositeDisposable.add(
            apiClient.getDoctorInfo(role)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<DoctorGetResponse>(){
                    override fun onNext(t: DoctorGetResponse) {
                        response.value= Resource(Status.SUCCESS,t,null,null)
                    }

                    override fun onError(e: Throwable) {
                       if (e.message?.contains("401",true)==true){
                           Constants.setUnAuthorized(true)
                       }
                    }

                    override fun onComplete() {}

                }))
        response.value= Resource(Status.LOADING,null,null,null)



    }


    fun getPatient(
        username:String,
        response: MutableLiveData<Resource<DoctorMyPacientesResponseItem>>
    ){
        compositeDisposable.add(
            apiClient.getPatient(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableObserver<DoctorMyPacientesResponseItem>(){
                    override fun onNext(t:DoctorMyPacientesResponseItem) {
                        response.value= Resource(Status.SUCCESS,t,null,null)
                    }

                    override fun onError(e: Throwable) {
                        if (e.message?.contains("401",true)==true){
                            Constants.setUnAuthorized(true)
                        }
                    }

                    override fun onComplete() {}

                })
        )
    }


}