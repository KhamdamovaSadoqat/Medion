package uz.medion.ui.main.doctor.profileDoctor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.medion.data.model.doctor.DoctorControllerPostResponses
import uz.medion.data.model.doctor.DoctorsControllerPostBody
import uz.medion.data.model.remote.Resource
import uz.medion.data.network.RestApi
import uz.medion.data.repository.DocRepository

class ChangeProfileDoctorFragmentViewModel : ViewModel() {

    private val repo = DocRepository( )
    private var resultPost=MutableLiveData<Resource<DoctorControllerPostResponses>>()

    fun doctorInfo(
        doctorsControllerPostBody: DoctorsControllerPostBody
        ):LiveData<Resource<DoctorControllerPostResponses>>{
        repo.doctorInfo(
            doctorsControllerPostBody,
            resultPost
        )
       return resultPost
        }
}