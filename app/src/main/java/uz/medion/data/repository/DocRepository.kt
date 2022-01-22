package uz.medion.data.repository

import android.annotation.SuppressLint
import android.util.Log
import uz.medion.data.model.doctor.DoctorResponse
import uz.medion.data.network.RestApi
import java.util.concurrent.Flow

class DocRepository( private val service: RestApi) {
    private val TAG = "DocRepository"
    private var updateTime: Long = 0





    @SuppressLint("LogConditional")
    suspend fun addDoctorInfo(map: HashMap<String, String>): ArrayList<DoctorResponse> {
        val response = service.addDoctorInfo(map)
        Log.d(TAG, "addDoctorInfo: ${response.code()}")
        if (response.isSuccessful && response.body() != null) {
            return arrayListOf(response.body()!!)
        } else {
            Log.e(TAG, "addDoctorInfo: ${response.errorBody()} ")
        }
        return arrayListOf()

    }
}