package uz.medion.ui.main.user.myDoctors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.medion.data.repository.UserRepository
import uz.medion.data.model.DoctorResponse
import uz.medion.data.model.remote.Resource
import uz.medion.ui.base.BaseVM

class MyDoctorsVM: BaseVM() {
    private val repo = UserRepository()
    private val myDoctorsResponse = MutableLiveData<Resource<List<DoctorResponse>>>()
    private val setFavouriteDoctorsResponse = MutableLiveData<Resource<Boolean>>()

    fun getMyDoctors(): LiveData<Resource<List<DoctorResponse>>> {
        repo.getMyDoctors(myDoctorsResponse)
        return myDoctorsResponse
    }

    fun getMyDoctorsFavourite(): LiveData<Resource<List<DoctorResponse>>> {
        repo.getMyDoctorsFavourite(myDoctorsResponse)
        return myDoctorsResponse
    }

    fun postDoctorsFavourite(doctorId: Int): LiveData<Resource<Boolean>> {
        repo.postDoctorsFavourite(doctorId, setFavouriteDoctorsResponse)
        return setFavouriteDoctorsResponse
    }

}