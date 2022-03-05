package uz.medion.ui.splash.verification

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.medion.data.model.RegistrationRequest
import uz.medion.data.model.RegistrationResponse
import uz.medion.data.model.remote.Resource
import uz.medion.data.repository.AuthRepository
import uz.medion.ui.base.BaseVM

class VerificationVM: BaseVM() {
    private val responseOfRegistration = MutableLiveData<Resource<RegistrationResponse>>()
    private val repo = AuthRepository()

    fun verifyAndRegisterUser(
        requestId: String,
        code: String,
        registrationRequest: RegistrationRequest
    ): LiveData<Resource<RegistrationResponse>> {
        repo.verifyAndRegisterUser(
            requestId,
            code, registrationRequest,  responseOfRegistration)
        return responseOfRegistration
    }
}