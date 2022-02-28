package uz.medion.ui.main.doctor.personalArea

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.medion.data.model.doctor.DoctorGetResponse
import uz.medion.data.model.remote.Resource
import uz.medion.data.repository.DocRepository

class PersonalAreaVM : ViewModel() {
    private val repo = DocRepository()
    private val result = MutableLiveData<Resource<DoctorGetResponse>>()
    fun getDoctorInfo() :MutableLiveData<Resource<DoctorGetResponse>>{
        repo.getDoctorInfo(result)
        return result
    }

}