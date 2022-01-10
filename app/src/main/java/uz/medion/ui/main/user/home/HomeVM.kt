package uz.medion.ui.main.user.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.medion.data.Repository
import uz.medion.data.model.AboutClinicResponse
import uz.medion.data.model.SpecialityItemResponse
import uz.medion.data.model.remote.Resource
import uz.medion.ui.base.BaseVM

class HomeVM: BaseVM(){
    private val repo = Repository()
    private val aboutClinicResponse = MutableLiveData<Resource<AboutClinicResponse>>()
    private val specialityResponse = MutableLiveData<Resource<List<SpecialityItemResponse>>>()

    fun aboutClinic(): LiveData<Resource<AboutClinicResponse>> {
        repo.aboutClinic(aboutClinicResponse)
        return aboutClinicResponse
    }

    fun speciality(): LiveData<Resource<List<SpecialityItemResponse>>>{
        repo.speciality(specialityResponse)
        return specialityResponse
    }
}