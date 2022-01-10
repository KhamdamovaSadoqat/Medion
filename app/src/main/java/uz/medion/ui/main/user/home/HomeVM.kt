package uz.medion.ui.main.user.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.medion.data.Repository
import uz.medion.data.model.AboutClinic
import uz.medion.data.model.remote.Resource
import uz.medion.ui.base.BaseVM

class HomeVM: BaseVM(){
    private val repo = Repository()
    private val aboutClinicResult = MutableLiveData<Resource<AboutClinic>>()

    fun aboutClinic(): LiveData<Resource<AboutClinic>> {
        repo.aboutClinic(aboutClinicResult)
        return aboutClinicResult
    }
}