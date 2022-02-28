package uz.medion.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import uz.medion.data.constants.Constants
import uz.medion.data.model.DoctorCertificateResponse
import uz.medion.data.model.DoctorResponse
import uz.medion.data.model.doctor.*
import uz.medion.data.model.remote.Resource
import uz.medion.data.model.remote.Status
import uz.medion.data.network.CreateRetrofit
import uz.medion.data.retrofit.ApiClient

class DocRepository() {
    private val compositeDisposable = CompositeDisposable()
    private val apiClient = CreateRetrofit.getRetrofit()
    private val api=ApiClient.getApiClient()
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
        response: MutableLiveData<Resource<DoctorControllerPostResponses>>,
    ) {
        compositeDisposable.add(
            apiClient.doctorInfo(
                doctorsControllerPostBody
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<DoctorControllerPostResponses>() {
                    override fun onNext(t: DoctorControllerPostResponses) {
                        response.value = Resource(Status.SUCCESS, t, null, null)
                    }

                    override fun onError(e: Throwable) {
                        if (e.message?.contains("401", true) == true) {
                            Constants.setUnAuthorized(true)
                        }
                        response.value = Resource(Status.ERROR, null, e.message, e)
                    }

                    override fun onComplete() {}

                })
        )
        response.value = Resource(Status.LOADING, null, null, null)
        Log.d(TAG, "doctorInfo: $doctorsControllerPostBody")
    }

    fun getDoctorInfo(
        response: MutableLiveData<Resource<DoctorGetResponse>>,
    ) {
        compositeDisposable.add(
            apiClient.getDoctorInfo("DOCTOR","Bearer ${Constants.token}")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<DoctorGetResponse>() {
                    override fun onNext(t: DoctorGetResponse) {
                        response.value = Resource(Status.SUCCESS, t, null, null)
                    }

                    override fun onError(e: Throwable) {
                        if (e.message?.contains("401", true) == true) {
                            Constants.setUnAuthorized(true)
                        }
                    }

                    override fun onComplete() {}

                }))
        response.value = Resource(Status.LOADING, null, null, null)


    }


    fun myPatient(
        response: MutableLiveData<Resource<List<DoctorMyPacientesResponseItem>>>,
    ) {
        compositeDisposable.add(
            apiClient.myPatient("doctor@doctor.com","Bearer ${Constants.token}")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<List<DoctorMyPacientesResponseItem>>() {
                    override fun onNext(t: List<DoctorMyPacientesResponseItem>) {
                        response.value = Resource(Status.SUCCESS, t, null, null)
                    }

                    override fun onError(e: Throwable) {
                        if (e.message?.contains("401", true) == true) {
                            Constants.setUnAuthorized(true)
                        }
                    }

                    override fun onComplete() {}

                })
        )
    }

    fun putDoctorInfo(
        id: Int,
        doctorInfoRequest: DoctorPutBody,
        response: MutableLiveData<Resource<DoctorPutResponses>>,
    ) {
        compositeDisposable.add(
            apiClient.putDoctorInfo(
                id, doctorInfoRequest
            )
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<DoctorPutResponses>() {
                    override fun onNext(t: DoctorPutResponses) {
                        response.value = Resource(Status.SUCCESS, t, null, null)
                    }

                    override fun onError(e: Throwable) {
                        if (e.message?.contains("401", true) == true) {
                            Constants.setUnAuthorized(true)
                        }
                        response.value = Resource(Status.ERROR, null, e.message, e)

                    }

                    override fun onComplete() {}

                }
                )
        )
        response.value = Resource(Status.LOADING, null, null, null)
    }

    fun getCertificates(
        username: String,
        response:MutableLiveData<Resource<List<DoctorCertificateResponse>>>,
    ){
        compositeDisposable.add(
            apiClient.getCertificates(username)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableObserver<List<DoctorCertificateResponse>>(){
                    override fun onNext(t: List<DoctorCertificateResponse>) {
                        response.value= Resource(Status.SUCCESS,t,null,null)
                    }

                    override fun onError(e: Throwable) {
                        if (e.message?.contains("401", true) == true) {
                            Constants.setUnAuthorized(true)
                        }
                        response.value = Resource(Status.ERROR, null, e.message, e)

                    }

                    override fun onComplete() {}

                })
        )
        response.value= Resource(Status.LOADING,null,null,null)
    }


    fun setFavouritePatient(clientId:Int,
    response:MutableLiveData<Resource<Boolean>>){
compositeDisposable.add(
    apiClient.setFavouritePatient(clientId,"Bearer${Constants.token}")
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribeWith(object :DisposableObserver<Boolean>(){
            override fun onNext(t: Boolean) {
                response.value=Resource(Status.SUCCESS,t,null,null)

            }

            override fun onError(e: Throwable) {
                if (e.message?.contains("401", true) == true) {
                    Constants.setUnAuthorized(true)
                }
                response.value = Resource(Status.ERROR, null, e.message, e)

            }

            override fun onComplete() { }

        })
)
        response.value= Resource(Status.LOADING,null,null,null)
    }
    fun getDoctorId(
        response:MutableLiveData<Resource<DoctorGetResponse>>
    ){
        compositeDisposable.add(
            api.getDoctorId(3, "Bearer ${Constants.token}")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableObserver<DoctorGetResponse>(){
                    override fun onNext(t: DoctorGetResponse) {
                        response.value= Resource(Status.SUCCESS,t,null,null)
                    }

                    override fun onError(e: Throwable) {
                        if (e.message?.contains("401", true) == true) {
                            Constants.setUnAuthorized(true)
                        }
                        response.value = Resource(Status.ERROR, null, e.message, e)

                    }

                    override fun onComplete() { }

                })
        )
        response.value= Resource(Status.LOADING,null,null,null)
    }
    fun getFavourite(
    response:MutableLiveData<Resource<List<DoctorMyPacientesResponseItem>>>)
    {
        compositeDisposable.add(
          apiClient.getFavourite("admin@admin.com","Bearer ${Constants.token}")
              .subscribeOn(Schedulers.io())
              .observeOn(AndroidSchedulers.mainThread())
              .subscribeWith(object :DisposableObserver<List<DoctorMyPacientesResponseItem>>(){
                  override fun onNext(t: List<DoctorMyPacientesResponseItem>) {
                      response.value= Resource(Status.SUCCESS,t,null,null)
                  }

                  override fun onError(e: Throwable) {
                      if (e.message?.contains("401", true) == true) {
                          Constants.setUnAuthorized(true)
                      }
                      response.value = Resource(Status.ERROR, null, e.message, e)

                  }

                  override fun onComplete() {}

              })
        )
        response.value= Resource(Status.LOADING,null,null,null)

    }

    fun patientById(patientId:Int,response:MutableLiveData<Resource<DoctorResponse>>){
        compositeDisposable.add(
            apiClient.patientById(patientId,"Bearer ${Constants.token}")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object :DisposableObserver<DoctorResponse>(){
                    override fun onNext(t: DoctorResponse) {
                        response.value= Resource(Status.SUCCESS,t,null,null)

                    }

                    override fun onError(e: Throwable) {
                        if (e.message?.contains("401", true) == true) {
                            Constants.setUnAuthorized(true)
                        }
                        response.value = Resource(Status.ERROR, null, e.message, e)

                    }

                    override fun onComplete() { }

                })
        )
        response.value= Resource(Status.LOADING,null,null,null)

    }
}