package uz.medion.ui.main.user.aboutDoctor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.medion.data.repository.Repository
import uz.medion.data.model.AboutDoctorCommentItem
import uz.medion.data.model.remote.Resource
import uz.medion.ui.base.BaseVM

class AboutDoctorVM: BaseVM() {

    private val repo = Repository()
    private val aboutDoctorCommentItem = MutableLiveData<Resource<AboutDoctorCommentItem>>()

    fun sendComment(commentItem: AboutDoctorCommentItem){
        repo.sendComment(commentItem, aboutDoctorCommentItem)
    }

    fun getComment(): LiveData<Resource<AboutDoctorCommentItem>> {
        return aboutDoctorCommentItem
    }
}