package uz.medion.ui.main.user.changeNumber

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.medion.data.model.EmailResponseResponse
import uz.medion.data.model.RegistrationCreateRequest
import uz.medion.data.model.remote.Resource
import uz.medion.data.repository.UserRepository
import uz.medion.ui.base.BaseVM

class ChangeNumberVM: BaseVM() {

    private val repo = UserRepository()
    private val resetPhoneResponse = MutableLiveData<Resource<EmailResponseResponse>>()

    fun postResetPhone(registrationCreateRequest: RegistrationCreateRequest): LiveData<Resource<EmailResponseResponse>> {
        repo.postResetPhone(resetPhoneResponse, registrationCreateRequest)
        return resetPhoneResponse
    }
}