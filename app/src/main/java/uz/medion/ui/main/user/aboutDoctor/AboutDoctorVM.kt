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
    private val monthlyTimeResponse = MutableLiveData<Resource<List<MonthlyTimeResponse>>>()
    private val commentsResponse = MutableLiveData<Resource<List<CommentResponse>>>()
    private val doctorByIdResponse = MutableLiveData<Resource<DoctorResponse>>()
    private val certificateResponse = MutableLiveData<Resource<List<DoctorCertificateResponse>>>()

    fun doctorById(doctorId: Int): LiveData<Resource<DoctorResponse>>{
        repo.doctorsById(doctorId, doctorByIdResponse)
        return  doctorByIdResponse
    }

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

    fun monthlyTime(date: String, doctorId: Int): LiveData<Resource<List<MonthlyTimeResponse>>>{
        repo.monthlyTime(date, doctorId, monthlyTimeResponse)
        return  monthlyTimeResponse
    }

    fun getCertificate(username: String): LiveData<Resource<List<DoctorCertificateResponse>>> {
        repo.getCertificates(username, certificateResponse)
        return certificateResponse
    }

}