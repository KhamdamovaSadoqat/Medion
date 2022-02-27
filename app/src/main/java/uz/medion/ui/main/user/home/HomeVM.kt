package uz.medion.ui.main.user.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.medion.data.repository.UserRepository
import uz.medion.data.model.AboutClinicResponse
import uz.medion.data.model.SpecialityItemResponse
import uz.medion.data.model.remote.Resource
import uz.medion.ui.base.BaseVM

class HomeVM: BaseVM(){
    private val repo = UserRepository()
    private val aboutClinicResponse = MutableLiveData<Resource<AboutClinicResponse>>()
    private val specialityResponse = MutableLiveData<Resource<List<SpecialityItemResponse>>>()

    fun getAboutClinic(): LiveData<Resource<AboutClinicResponse>> {
        repo.getAboutClinic(aboutClinicResponse)
        return aboutClinicResponse
    }

    fun getSpeciality(): LiveData<Resource<List<SpecialityItemResponse>>>{
        repo.getSpeciality(specialityResponse)
        return specialityResponse
    }
}