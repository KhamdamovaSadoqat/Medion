package uz.medion.ui.main.user.personalAccount

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.medion.data.model.EmailResponseResponse
import uz.medion.data.model.ProfileResponse
import uz.medion.data.model.RegistrationCreateRequest
import uz.medion.data.model.remote.Resource
import uz.medion.data.repository.UserRepository
import uz.medion.ui.base.BaseVM

class PersonalAccountVM: BaseVM() {
    private val repo = UserRepository()
    private val profileResponse = MutableLiveData<Resource<ProfileResponse>>()

    fun getProfile(): LiveData<Resource<ProfileResponse>> {
        repo.getProfile(profileResponse)
        return profileResponse
    }


}