package uz.medion.ui.main.user.aboutDoctor

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.medion.data.model.*
import uz.medion.data.model.remote.Resource
import uz.medion.data.repository.UserRepository
import uz.medion.ui.base.BaseVM

class AboutDoctorVM: BaseVM() {

    private val repo = UserRepository()
    private val monthlyDateResponse = MutableLiveData<Resource<List<MonthlyDateResponse>>>()
    private val monthlyTimeResponse = MutableLiveData<Resource<List<MonthlyTimeResponse>>>()
    private val commentsResponse = MutableLiveData<Resource<List<CommentResponse>>>()
    private val doctorByIdResponse = MutableLiveData<Resource<DoctorResponse>>()
    private val certificateResponse = MutableLiveData<Resource<List<DoctorCertificateResponse>>>()

    fun getDoctorById(doctorId: Int): LiveData<Resource<DoctorResponse>>{
        repo.getDoctorById(doctorId, doctorByIdResponse)
        return  doctorByIdResponse
    }

    fun postComment(commentRequest: CommentRequest): LiveData<Resource<List<CommentResponse>>>{
        repo.postComment(commentRequest, commentsResponse)
        return commentsResponse
    }

    fun getComments(doctorId: Int): LiveData<Resource<List<CommentResponse>>> {
        repo.getComments(doctorId, commentsResponse)
        return commentsResponse
    }

    fun getMonthlyDate(doctorId: Int): LiveData<Resource<List<MonthlyDateResponse>>>{
        repo.getMonthlyDate(doctorId, monthlyDateResponse)
        return monthlyDateResponse
    }

    fun getMonthlyTime(date: String, doctorId: Int): LiveData<Resource<List<MonthlyTimeResponse>>>{
        repo.getMonthlyTime(date, doctorId, monthlyTimeResponse)
        return  monthlyTimeResponse
    }

    fun getCertificate(username: String): LiveData<Resource<List<DoctorCertificateResponse>>> {
        repo.getCertificates(username, certificateResponse)
        return certificateResponse
    }

}