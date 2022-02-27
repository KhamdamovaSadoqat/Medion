package uz.medion.ui.main.user.ourDoctors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.medion.data.repository.UserRepository
import uz.medion.data.model.DoctorResponse
import uz.medion.data.model.SpecialityItemResponse
import uz.medion.data.model.SubSpecialityResponse
import uz.medion.data.model.remote.Resource
import uz.medion.ui.base.BaseVM

class OurDoctorsVM: BaseVM() {
    private val repo = UserRepository()
    private val doctorResponse = MutableLiveData<Resource<List<DoctorResponse>>>()
    private val specialityResponse = MutableLiveData<Resource<List<SpecialityItemResponse>>>()
    private val subSpecialityResponse = MutableLiveData<Resource<List<SubSpecialityResponse>>>()
    private val filterDoctorsResponse = MutableLiveData<Resource<List<DoctorResponse>>>()

    fun getDoctorBySpeciality(url: String): LiveData<Resource<List<DoctorResponse>>> {
        repo.getDoctorBySpeciality(url, doctorResponse)
        return doctorResponse
    }

    fun getSpeciality(): LiveData<Resource<List<SpecialityItemResponse>>>{
        repo.getSpeciality(specialityResponse)
        return specialityResponse
    }

    fun getSubSpeciality(specialityId: Int): LiveData<Resource<List<SubSpecialityResponse>>>{
        repo.getSubSpeciality(specialityId, subSpecialityResponse)
        return subSpecialityResponse
    }

    fun getFilteredDoctors(date: String, specialityId: Int, subSpecialityId: Int):LiveData<Resource<List<DoctorResponse>>>{
        repo.getFilteredDoctors(date, specialityId, subSpecialityId, filterDoctorsResponse)
        return filterDoctorsResponse
    }

}