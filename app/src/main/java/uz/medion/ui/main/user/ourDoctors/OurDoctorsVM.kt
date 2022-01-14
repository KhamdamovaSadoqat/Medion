package uz.medion.ui.main.user.ourDoctors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.medion.data.Repository
import uz.medion.data.model.DoctorResponse
import uz.medion.data.model.SpecialityItemResponse
import uz.medion.data.model.remote.Resource
import uz.medion.ui.base.BaseVM

class OurDoctorsVM: BaseVM() {
    private val repo = Repository()
    private val doctorResponse = MutableLiveData<Resource<List<DoctorResponse>>>()
    private val specialityResponse = MutableLiveData<Resource<List<SpecialityItemResponse>>>()

    fun doctorBySpeciality(url: String): LiveData<Resource<List<DoctorResponse>>> {
        repo.doctorBySpeciality(url, doctorResponse)
        return doctorResponse
    }

    fun speciality(): LiveData<Resource<List<SpecialityItemResponse>>>{
        repo.speciality(specialityResponse)
        return specialityResponse
    }

}