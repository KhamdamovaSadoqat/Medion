package uz.medion.data.model

import com.google.gson.annotations.SerializedName

data class RegistrationRequest(

	@field:SerializedName("username")
	val username: String? = null,

	@field:SerializedName("fullName")
	val fullName: String? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("phoneNumber")
	val phoneNumber: String? = null

)
