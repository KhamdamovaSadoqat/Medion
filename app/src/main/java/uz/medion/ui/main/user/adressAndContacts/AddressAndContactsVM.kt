package uz.medion.ui.main.user.adressAndContacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.medion.data.Repository
import uz.medion.data.model.BranchResponse
import uz.medion.data.model.remote.Resource
import uz.medion.ui.base.BaseVM

class AddressAndContactsVM: BaseVM() {
    private val repo = Repository()
    private val branchResponse = MutableLiveData<Resource<List<BranchResponse>>>()

    fun branch(): LiveData<Resource<List<BranchResponse>>> {
        repo.branch(branchResponse)
        return branchResponse
    }
}