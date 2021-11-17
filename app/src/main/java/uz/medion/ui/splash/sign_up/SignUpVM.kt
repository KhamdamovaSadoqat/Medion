package uz.medion.ui.splash.sign_up

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.medion.data.Repository
import uz.medion.data.model.IsRegistrationFlowAvailable
import uz.medion.data.model.remote.Resource
import uz.medion.ui.base.BaseVM

class SignUpVM: BaseVM() {
    private val isRegistrationFlowAvailable = MutableLiveData<Resource<IsRegistrationFlowAvailable>>()
    private val repo = Repository()

    fun getIsRegistrationFlowAvailable(userName: String): LiveData<Resource<IsRegistrationFlowAvailable>> {
        repo.getIsRegistrationFlowAvailable(userName, isRegistrationFlowAvailable)
        return isRegistrationFlowAvailable
    }
}