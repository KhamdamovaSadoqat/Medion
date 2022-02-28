package uz.medion.ui.main.doctor.profileDoctor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.medion.data.model.doctor.DoctorControllerPostResponses
import uz.medion.data.model.doctor.DoctorGetResponse
import uz.medion.data.model.doctor.DoctorsControllerPostBody
import uz.medion.data.model.remote.Resource
import uz.medion.data.network.RestApi
import uz.medion.data.repository.DocRepository
import uz.medion.ui.base.BaseVM

class ChangeProfileDoctorFragmentViewModel : BaseVM() {

    private val repo = DocRepository( )
    private var resultPost=MutableLiveData<Resource<DoctorControllerPostResponses>>()
    private var result=MutableLiveData<Resource<DoctorGetResponse>>()

    fun doctorInfo(
        doctorsControllerPostBody: DoctorsControllerPostBody
        ):LiveData<Resource<DoctorControllerPostResponses>>{
        repo.doctorInfo(
            doctorsControllerPostBody,
            resultPost
        )
       return resultPost
        }

    fun getDoctorId():LiveData<Resource<DoctorGetResponse>>{
        repo.getDoctorId(result)
        return result
    }
}