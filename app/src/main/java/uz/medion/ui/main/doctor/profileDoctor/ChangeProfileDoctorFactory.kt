package uz.medion.ui.main.doctor.profileDoctor

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import uz.medion.data.network.RestApi

class ChangeProfileDoctorFactory (private val service:RestApi):ViewModelProvider.Factory{
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ChangeProfileDoctorFragmentViewModel::class.java)){
            return ChangeProfileDoctorFragmentViewModel(service) as T
        } else {
            throw IllegalArgumentException("ChangeProfileDoctorViewModel not found")
        }
    }

}