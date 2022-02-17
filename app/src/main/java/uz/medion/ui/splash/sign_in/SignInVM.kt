package uz.medion.ui.splash.sign_in

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.medion.data.Repository
import uz.medion.data.model.Login
import uz.medion.data.model.UserLogin
import uz.medion.data.model.remote.Resource
import uz.medion.ui.base.BaseVM

class SignInVM: BaseVM() {
    private val repo = Repository()
    private val loginResult = MutableLiveData<Resource<UserLogin>>()

    fun login(login: Login): LiveData<Resource<UserLogin>> {
        repo.login(login, loginResult)
        return loginResult
    }

}