package uz.medion.data.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.HttpException
import uz.medion.data.constants.Constants
import uz.medion.data.model.*
import uz.medion.data.model.remote.Resource
import uz.medion.data.model.remote.Status
import uz.medion.data.retrofit.auth.AuthApiClient

class AuthRepository {

    private val compositeDisposable = CompositeDisposable()
    private val apiClient = AuthApiClient.getApiClient()

    fun registrationCreate(
        phoneNumber: RegistrationCreateRequest,
        response: MutableLiveData<Resource<EmailResponseResponse>>,
    ) {
        compositeDisposable.add(
            apiClient.requestMail(phoneNumber)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<EmailResponseResponse>() {
                    override fun onNext(t: EmailResponseResponse) {
                        response.value = Resource(Status.SUCCESS, t, null, null)
                    }

                    override fun onError(e: Throwable) {
                        if (e is HttpException) {
                            val errorBody = e.response()?.errorBody()
                            val error =
                                Gson().fromJson<ErrorResponse>(errorBody!!.charStream(),
                                    object : TypeToken<ErrorResponse>() {}.type)
                            response.value = Resource(Status.ERROR, null, error.message, e)
                            Log.d("----------", "onError: $error")
                        }
                    }

                    override fun onComplete() {}

                })
        )
        response.value = Resource(Status.LOADING, null, null, null)
    }

    fun getIsRegistrationFlowAvailable(
        userName: String,
        response: MutableLiveData<Resource<IsRegistrationFlowAvailable>>,
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

    fun verifyAndRegisterUser(
        requestId: String,
        code: String,
        registrationRequest: RegistrationRequest,
        response: MutableLiveData<Resource<RegistrationResponse>>,
    ) {
        compositeDisposable.add(
            apiClient.verifyAndRegisterUser(
                requestId,
                code, registrationRequest)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<RegistrationResponse>() {
                    override fun onNext(t: RegistrationResponse) {
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

    fun login(
        login: Login,
        response: MutableLiveData<Resource<UserLogin>>,
    ) {
        compositeDisposable.add(
            apiClient.login(login)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<UserLogin>() {
                    override fun onNext(t: UserLogin) {
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