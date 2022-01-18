package uz.medion.ui.main.user.aboutDoctor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.medion.data.Repository
import uz.medion.data.model.*
import uz.medion.data.model.remote.Resource
import uz.medion.ui.base.BaseVM

class AboutDoctorVM: BaseVM() {

    private val repo = Repository()
    private val monthlyDateResponse = MutableLiveData<Resource<List<MonthlyDateResponse>>>()
    private val monthlyTimeResponse = MutableLiveData<Resource<DataResponse>>()
    private val commentsResponse = MutableLiveData<Resource<List<CommentResponse>>>()


    fun sendComment(comment: SendComment): LiveData<Resource<List<CommentResponse>>>{
        repo.sendComment(comment, commentsResponse)
        return commentsResponse
    }

    fun comments(doctorId: Int): LiveData<Resource<List<CommentResponse>>> {
        repo.comments(doctorId, commentsResponse)
        return commentsResponse
    }

    fun monthlyDate(doctorId: Int): LiveData<Resource<List<MonthlyDateResponse>>>{
        repo.monthlyDate(doctorId, monthlyDateResponse)
        return monthlyDateResponse
    }

    fun monthlyTime(date: String, doctorId: Int): LiveData<Resource<DataResponse>>{
        repo.monthlyTime(date, doctorId, monthlyTimeResponse)
        return  monthlyTimeResponse
    }

}