package uz.medion.ui.main.user.esteticMedicine

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.medion.data.model.EsteticMedicineResponse
import uz.medion.data.model.remote.Resource
import uz.medion.data.repository.UserRepository
import uz.medion.ui.base.BaseVM

class EsteticMedicineVM: BaseVM() {

    val repo = UserRepository()
    private val esteticMedicine = MutableLiveData<Resource<List<EsteticMedicineResponse>>>()

    fun getEsteticMedicine(): LiveData<Resource<List<EsteticMedicineResponse>>> {
        repo.getEsteticMedicine(esteticMedicine)
        return esteticMedicine
    }
}