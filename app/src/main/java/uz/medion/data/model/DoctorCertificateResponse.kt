package uz.medion.data.model

import com.google.gson.annotations.SerializedName

data class DoctorCertificateResponse(

	@field:SerializedName("id")
	val id: Int,

	@field:SerializedName("url")
	val url: String
)
