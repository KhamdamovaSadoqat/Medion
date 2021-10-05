package uz.medion.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.bumptech.glide.load.engine.Resource
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import io.reactivex.schedulers.Schedulers
import uz.medion.data.model.AboutDoctorCommentItem
import uz.medion.data.model.SUCCESSTEST
import uz.medion.data.retrofit.ApiClient

class Repository {

    private val compositeDisposable = CompositeDisposable()
    private val apiClient = ApiClient.getApiClient()

    fun sendComment(commentItem: AboutDoctorCommentItem, response: MutableLiveData<Resource<AboutDoctorCommentItem>>) {
        compositeDisposable.add(
            apiClient.sendComment(1000, "HERE SHOULD BE SOMESHING", "TOKEEEEN")
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

}