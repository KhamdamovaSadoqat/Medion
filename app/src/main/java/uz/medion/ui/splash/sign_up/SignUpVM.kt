package uz.medion.ui.splash.sign_up

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.medion.data.model.IsRegistrationFlowAvailable
import uz.medion.data.model.RegistrationCreateRequest
import uz.medion.data.model.EmailResponseResponse
import uz.medion.data.model.remote.Resource
import uz.medion.data.repository.AuthRepository
import uz.medion.ui.base.BaseVM

class SignUpVM : BaseVM() {
    private val isRegistrationFlowAvailable =
        MutableLiveData<Resource<IsRegistrationFlowAvailable>>()
    private val registrationCreate = MutableLiveData<Resource<EmailResponseResponse>>()
    private val repo = AuthRepository()

    fun getIsRegistrationFlowAvailable(userName: String): LiveData<Resource<IsRegistrationFlowAvailable>> {
        repo.getIsRegistrationFlowAvailable(userName, isRegistrationFlowAvailable)
        return isRegistrationFlowAvailable
    }

    fun registrationCreate(phoneNumber: RegistrationCreateRequest): LiveData<Resource<EmailResponseResponse>> {
        repo.registrationCreate(phoneNumber, registrationCreate)
        return registrationCreate
    }
}