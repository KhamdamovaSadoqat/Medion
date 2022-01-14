package uz.medion.ui.main.user.aboutDoctor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.medion.data.Repository
import uz.medion.data.model.AboutDoctorCommentItem
import uz.medion.data.model.MonthlyDateResponse
import uz.medion.data.model.remote.Resource
import uz.medion.ui.base.BaseVM

class AboutDoctorVM: BaseVM() {

    private val repo = Repository()
    private val aboutDoctorCommentItem = MutableLiveData<Resource<AboutDoctorCommentItem>>()
    private val monthlyDateResponse = MutableLiveData<Resource<List<MonthlyDateResponse>>>()

    fun sendComment(commentItem: AboutDoctorCommentItem){
        repo.sendComment(commentItem, aboutDoctorCommentItem)
    }

    fun getComment(): LiveData<Resource<AboutDoctorCommentItem>> {
        return aboutDoctorCommentItem
    }

    fun monthlyDate(doctorId: Int): LiveData<Resource<List<MonthlyDateResponse>>>{
        repo.monthlyDate(doctorId, monthlyDateResponse)
        return monthlyDateResponse
    }

}