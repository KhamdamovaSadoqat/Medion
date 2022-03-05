package uz.medion.ui.main.user.adressAndContacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.medion.data.repository.UserRepository
import uz.medion.data.model.BranchResponse
import uz.medion.data.model.remote.Resource
import uz.medion.ui.base.BaseVM

class AddressAndContactsVM: BaseVM() {
    private val repo = UserRepository()
    private val branchResponse = MutableLiveData<Resource<List<BranchResponse>>>()

    fun getBranch(): LiveData<Resource<List<BranchResponse>>> {
        repo.getBranch(branchResponse)
        return branchResponse
    }
}