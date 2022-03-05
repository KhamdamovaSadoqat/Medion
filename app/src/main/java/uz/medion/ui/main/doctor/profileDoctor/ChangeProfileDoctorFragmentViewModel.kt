package uz.medion.ui.main.doctor.profileDoctor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.medion.data.model.doctor.*
import uz.medion.data.model.remote.Resource
import uz.medion.data.network.RestApi
import uz.medion.data.repository.DocRepository
import uz.medion.ui.base.BaseVM

class ChangeProfileDoctorFragmentViewModel : BaseVM() {

    private val repo = DocRepository( )
    private var resultPost=MutableLiveData<Resource<DoctorPutResponses>>()
    private var result=MutableLiveData<Resource<DoctorGetResponse>>()

//    fun doctorInfo(
//        doctorsControllerPostBody: DoctorsControllerPostBody
//        ):LiveData<Resource<DoctorControllerPostResponses>>{
//        repo.doctorInfo(
//            doctorsControllerPostBody,
//            resultPost
//        )
//       return resultPost
//        }

    fun getDoctorId():LiveData<Resource<DoctorGetResponse>>{
        repo.getDoctorId(result)
        return result
    }
    fun putDoctorInfo(
        id:Int,
    doctorPutBody:DoctorPutBody
    ):LiveData<Resource<DoctorPutResponses>>{
        repo.putDoctorInfo(
            id,
            doctorPutBody,
            resultPost
        )
        return resultPost
    }
}