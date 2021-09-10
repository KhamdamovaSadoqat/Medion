package uz.medion.data

import androidx.lifecycle.MutableLiveData
import io.reactivex.disposables.CompositeDisposable
import uz.medion.data.retrofit.ApiClient

class Repository {

    private val compositeDisposable = CompositeDisposable()
    private val apiClient = ApiClient.getApiClient()

}