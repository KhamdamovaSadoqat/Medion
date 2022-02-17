package uz.medion.data.model

import com.google.gson.annotations.SerializedName

data class RegistrationCreateRequest(

	@field:SerializedName("username")
	val username: String
)
