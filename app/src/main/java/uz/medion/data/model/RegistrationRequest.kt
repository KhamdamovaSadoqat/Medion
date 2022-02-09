package uz.medion.data.model

import com.google.gson.annotations.SerializedName

data class RegistrationRequest(

	@field:SerializedName("birthDate")
	val birthDate: String? = null,

	@field:SerializedName("fullName")
	val fullName: String? = null,

	@field:SerializedName("gender")
	val gender: String? = null,

	@field:SerializedName("image")
	val image: String? = null,

	@field:SerializedName("password")
	val password: String? = null,

	@field:SerializedName("phoneNumber")
	val phoneNumber: String? = null,

	@field:SerializedName("username")
	val username: String? = null,

)
