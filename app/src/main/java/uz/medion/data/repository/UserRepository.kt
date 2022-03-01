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
import uz.medion.data.retrofit.user.UserApiClient

class UserRepository {

    private val compositeDisposable = CompositeDisposable()
    private val apiClient = UserApiClient.getApiClient()

    fun getAboutClinic(response: MutableLiveData<Resource<AboutClinicResponse>>) {
        compositeDisposable.add(
            apiClient.getAboutClinic("Bearer ${Constants.token}")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<AboutClinicResponse>() {
                    override fun onNext(t: AboutClinicResponse) {
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

    fun getSpeciality(response: MutableLiveData<Resource<List<SpecialityItemResponse>>>) {
        compositeDisposable.add(
            apiClient.getSpeciality("Bearer ${Constants.token}")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<List<SpecialityItemResponse>>() {
                    override fun onNext(t: List<SpecialityItemResponse>) {
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

    fun getSubSpeciality(
        specialityId: Int,
        response: MutableLiveData<Resource<List<SubSpecialityResponse>>>,
    ) {
        compositeDisposable.add(
            apiClient.getSubSpeciality(specialityId, "Bearer ${Constants.token}")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<List<SubSpecialityResponse>>() {
                    override fun onNext(t: List<SubSpecialityResponse>) {
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

    fun getFilteredDoctors(
        date: String,
        specialityId: Int,
        subSpecialityId: Int,
        response: MutableLiveData<Resource<List<DoctorResponse>>>,
    ) {
        compositeDisposable.add(
            apiClient.getFilteredDoctors(date, specialityId, subSpecialityId, "Bearer ${Constants.token}")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<List<DoctorResponse>>() {
                    override fun onNext(t: List<DoctorResponse>) {
                        response.value = Resource(Status.SUCCESS, t, null, null)
                    }

                    override fun onError(e: Throwable) {
                        if (e.message?.contains("401", true) == true) {
                            Constants.setUnAuthorized(true)
                        }
                        response.value = Resource(Status.ERROR, null, e.message, e)
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

    fun getDoctorBySpeciality(url: String, response: MutableLiveData<Resource<List<DoctorResponse>>>) {
        compositeDisposable.add(
            apiClient.getDoctorsBySpeciality(url, "Bearer ${Constants.token}")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<List<DoctorResponse>>() {
                    override fun onNext(t: List<DoctorResponse>) {
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

    fun getDoctorById(doctorId: Int, response: MutableLiveData<Resource<DoctorResponse>>) {
        compositeDisposable.add(
            apiClient.getDoctorById(doctorId, "Bearer ${Constants.token}")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<DoctorResponse>() {
                    override fun onNext(t: DoctorResponse) {
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

    fun getMonthlyDate(doctorId: Int, response: MutableLiveData<Resource<List<MonthlyDateResponse>>>) {
        compositeDisposable.add(
            apiClient.getMonthlyDate(doctorId, "Bearer ${Constants.token}")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<List<MonthlyDateResponse>>() {
                    override fun onNext(t: List<MonthlyDateResponse>) {
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

    fun getMonthlyTime(
        date: String,
        doctorId: Int,
        response: MutableLiveData<Resource<List<MonthlyTimeResponse>>>,
    ) {
        compositeDisposable.add(
            apiClient.getMonthlyTime(date, doctorId, "Bearer ${Constants.token}")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<List<MonthlyTimeResponse>>() {
                    override fun onNext(t: List<MonthlyTimeResponse>) {
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

    fun getBranch(response: MutableLiveData<Resource<List<BranchResponse>>>) {
        compositeDisposable.add(
            apiClient.getBranch("Bearer ${Constants.token}")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<List<BranchResponse>>() {
                    override fun onNext(t: List<BranchResponse>) {
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

    fun getComments(doctorId: Int, response: MutableLiveData<Resource<List<CommentResponse>>>) {
        compositeDisposable.add(
            apiClient.getComments(doctorId, "Bearer ${Constants.token}")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<List<CommentResponse>>() {
                    override fun onNext(t: List<CommentResponse>) {
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

    fun postComment(
        commentRequest: CommentRequest,
        response: MutableLiveData<Resource<List<CommentResponse>>>,
    ) {
        compositeDisposable.add(
            apiClient.postComment(commentRequest, "Bearer ${Constants.token}")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<List<CommentResponse>>() {
                    override fun onNext(t: List<CommentResponse>) {
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

    fun getCertificates(
        usermame: String,
        response: MutableLiveData<Resource<List<DoctorCertificateResponse>>>,
    ) {
        compositeDisposable.add(
            apiClient.getCertificate(usermame, "Bearer ${Constants.token}")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<List<DoctorCertificateResponse>>() {
                    override fun onNext(t: List<DoctorCertificateResponse>) {
                        response.value = Resource(Status.SUCCESS, t, null, null)
                    }

                    override fun onError(e: Throwable) {
                        if (e.message?.contains("401", true) == true) {
                            Constants.setUnAuthorized(true)
                        }
                        response.value = Resource(Status.ERROR, null, e.message, e)
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

    fun getMyDoctors(
        response: MutableLiveData<Resource<List<DoctorResponse>>>,
    ) {
        compositeDisposable.add(
            apiClient.getMyDoctors("user@user.com", "Bearer ${Constants.token}")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<List<DoctorResponse>>() {
                    override fun onNext(t: List<DoctorResponse>) {
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

    fun getMyDoctorsFavourite(
        response: MutableLiveData<Resource<List<DoctorResponse>>>,
    ) {
        compositeDisposable.add(
            apiClient.getMyDoctorsFavourite("user@user.com", "Bearer ${Constants.token}")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<List<DoctorResponse>>() {
                    override fun onNext(t: List<DoctorResponse>) {
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

    fun postDoctorsFavourite(
        doctorId: Int,
        response: MutableLiveData<Resource<Boolean>>,
    ) {
        compositeDisposable.add(
            apiClient.postDoctorFavourite(doctorId, "Bearer ${Constants.token}")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<Boolean>() {
                    override fun onNext(t: Boolean) {
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

    fun getBookedEvent(response: MutableLiveData<Resource<List<BookingResponse>>>) {
        compositeDisposable.add(
            apiClient.getBookedEvent("user@user.com", "Bearer ${Constants.token}")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<List<BookingResponse>>() {
                    override fun onNext(t: List<BookingResponse>) {
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

    fun getEsteticMedicine(response: MutableLiveData<Resource<List<EsteticMedicineResponse>>>) {
        compositeDisposable.add(
            apiClient.getEsteticMedicine("Bearer ${Constants.token}")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableObserver<List<EsteticMedicineResponse>>() {
                    override fun onNext(t: List<EsteticMedicineResponse>) {
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