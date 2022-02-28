package uz.medion.ui.main.doctor.myPatient

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import uz.medion.data.model.doctor.DoctorGetFavouritesResponseItem
import uz.medion.data.model.doctor.DoctorMyPacientesResponseItem
import uz.medion.data.model.remote.Resource
import uz.medion.data.repository.DocRepository
import uz.medion.ui.base.BaseVM

class MyPatientViewModel : BaseVM() {
    private val repo =DocRepository()
    private val myPatientResponse=MutableLiveData<Resource<List<DoctorMyPacientesResponseItem>>>()
    private val getFavouriteResponse=MutableLiveData<Resource<List<DoctorMyPacientesResponseItem>>>()
    private var setFavouritePatientResponse=MutableLiveData<Resource<Boolean>>()

fun myPatient():LiveData<Resource<List<DoctorMyPacientesResponseItem>>>{
    repo.myPatient(myPatientResponse)
    return myPatientResponse
}
   fun getFavourite():LiveData<Resource<List<DoctorMyPacientesResponseItem>>>{
       repo.getFavourite(getFavouriteResponse)
       return getFavouriteResponse
   }
    fun setPatientFavourite(clientId :Int):LiveData<Resource<Boolean>> {
        repo.setFavouritePatient(clientId,setFavouritePatientResponse)
        return setFavouritePatientResponse

    }
}