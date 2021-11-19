package uz.medion.data

import androidx.lifecycle.MutableLiveData
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import uz.medion.data.constants.Constants
import uz.medion.data.model.AboutDoctorCommentItem
import uz.medion.data.model.IsRegistrationFlowAvailable
import uz.medion.data.model.ResponseOfRequestEmail
import uz.medion.data.model.SUCCESSTEST
import uz.medion.data.model.remote.Resource
import uz.medion.data.model.remote.Status
import uz.medion.data.retrofit.ApiClient

class Repository {

    private val compositeDisposable = CompositeDisposable()
    private val apiClient = ApiClient.getApiClient()

    fun sendComment(
        commentItem: AboutDoctorCommentItem,
        response: MutableLiveData<Resource<AboutDoctorCommentItem>>
    ) {
        compositeDisposable.add(
            apiClient.sendComment(1000, "HERE SHOULD BE SOMETHING", "TOKEN")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<SUCCESSTEST>() {
                    override fun onNext(t: SUCCESSTEST) {
//                        Log.i("---------", "onNext: ${t.statusCode}")
//                        if (t.statusCode == 200) {
//                            response.value = Resource(Status.SUCCESS, t, null, null)
//                        } else {
//                            response.value = Resource(Status.ERROR, t, t.message, null)
//                        }
                    }

                    override fun onError(e: Throwable) {
//                        Log.i("---------", "onNext: ${e.message}")
//
//                        response.value = Resource(Status.ERROR, null, e.message, e)
                    }

                    override fun onComplete() {}
                })
        )
    }

    fun responseOfRequestEmail(
        email: String,
        response: MutableLiveData<Resource<ResponseOfRequestEmail>>
    ) {
        compositeDisposable.add(
            apiClient.requestMail(email)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<ResponseOfRequestEmail>() {
                    override fun onNext(t: ResponseOfRequestEmail) {
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
    }

    fun getIsRegistrationFlowAvailable(
        userName: String,
        response: MutableLiveData<Resource<IsRegistrationFlowAvailable>>
    ) {
        compositeDisposable.add(
            apiClient.isRegistrationFlowAvailable(userName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<IsRegistrationFlowAvailable>() {
                    override fun onNext(t: IsRegistrationFlowAvailable) {
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
    }

}