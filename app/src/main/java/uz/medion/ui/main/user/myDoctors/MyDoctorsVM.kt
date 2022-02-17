package uz.medion.ui.main.user.myDoctors

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.medion.data.Repository
import uz.medion.data.model.DoctorResponse
import uz.medion.data.model.remote.Resource
import uz.medion.ui.base.BaseVM

class MyDoctorsVM: BaseVM() {
    private val repo = Repository()
    private val myDoctorsResponse = MutableLiveData<Resource<List<DoctorResponse>>>()
    private val setFavouriteDoctorsResponse = MutableLiveData<Resource<Boolean>>()

    fun myDoctors(): LiveData<Resource<List<DoctorResponse>>> {
        repo.myDoctors(myDoctorsResponse)
        return myDoctorsResponse
    }

    fun myDoctorsFavourite(): LiveData<Resource<List<DoctorResponse>>> {
        repo.myDoctorsFavourite(myDoctorsResponse)
        return myDoctorsResponse
    }

    fun setDoctorsFavourite(doctorId: Int): LiveData<Resource<Boolean>> {
        repo.setDoctorsFavourite(doctorId, setFavouriteDoctorsResponse)
        return setFavouriteDoctorsResponse
    }

}