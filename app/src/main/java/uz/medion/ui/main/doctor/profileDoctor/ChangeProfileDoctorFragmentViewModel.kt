package uz.medion.ui.main.doctor.profileDoctor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import uz.medion.data.model.doctor.DoctorResponse
import uz.medion.data.network.RestApi
import uz.medion.data.repository.DocRepository

class ChangeProfileDoctorFragmentViewModel(service: RestApi) : ViewModel() {
    private val repo = DocRepository( service)
    private var result: LiveData<List<DoctorResponse>> = MutableLiveData()
    private var resultPost=MutableLiveData<ArrayList<DoctorResponse>>()



    fun addDoctorInfo(map:HashMap<String,String>):LiveData<ArrayList<DoctorResponse>>{
        viewModelScope.launch {
            resultPost.value= withContext(Dispatchers.IO){
                repo.addDoctorInfo(map)
            }!!
        }

return resultPost
    }
//    fun postResult():{
//        return resultPost
//    }
}