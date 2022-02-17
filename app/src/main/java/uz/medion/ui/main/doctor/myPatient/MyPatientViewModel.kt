package uz.medion.ui.main.doctor.myPatient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.medion.data.model.doctor.DoctorMyPacientesResponseItem
import uz.medion.data.model.remote.Resource
import uz.medion.data.repository.DocRepository

class MyPatientViewModel : ViewModel() {
    private val repo =DocRepository()
    private var result=MutableLiveData<Resource<DoctorMyPacientesResponseItem>>()


    fun getPatient(
        username:String
    ):LiveData<Resource<DoctorMyPacientesResponseItem>>{
        repo.getPatient(
            username,result
        )
        return result
    }
}