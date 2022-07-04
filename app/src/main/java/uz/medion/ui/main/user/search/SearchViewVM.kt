package uz.medion.ui.main.user.search

import androidx.lifecycle.MutableLiveData
import uz.medion.data.model.DoctorResponse
import uz.medion.data.model.DoctorsItem
import uz.medion.data.model.ItemSearch
import uz.medion.data.model.remote.Resource
import uz.medion.data.model.remote.Status
import uz.medion.data.repository.UserRepository
import uz.medion.ui.base.BaseVM

class SearchViewVM:BaseVM() {
    private val repo = UserRepository()
    val joinDoctorsLiveData:MutableLiveData<Resource<List<DoctorResponse>>> = MutableLiveData()
    val joinSpecialityLiveData:MutableLiveData<Resource<List<ItemSearch>>> = MutableLiveData()
    val joinProfileSettingsLiveData:MutableLiveData<Resource<List<ItemSearch>>> = MutableLiveData()
    val joinMainsLiveData:MutableLiveData<Resource<List<ItemSearch>>> = MutableLiveData()

    fun onSearch(text:String){
        repo.getSearchableMyDoctors(text, joinDoctorsLiveData)
        repo.getSearchableSpeciality(text, joinSpecialityLiveData)
        repo.getSearchableProfileSettings(text, joinProfileSettingsLiveData)
        repo.getSearchableMains(text, joinMainsLiveData)
    }

    fun onEmptySearch(){
        joinDoctorsLiveData.value= Resource(Status.SUCCESS,emptyList<DoctorResponse>(), null, null)
        joinSpecialityLiveData.value= Resource(Status.SUCCESS, emptyList(), null, null)
        joinProfileSettingsLiveData.value= Resource(Status.SUCCESS, emptyList(), null, null)
        joinMainsLiveData.value= Resource(Status.SUCCESS, emptyList(), null, null)
    }

}