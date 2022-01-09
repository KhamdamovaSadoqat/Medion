package uz.medion.data.model

import com.google.gson.annotations.SerializedName

data class RegistrationResponse(

	@field:SerializedName("access_token")
	val accessToken: String? = null,

	@field:SerializedName("refresh_token")
	val refreshToken: String? = null
)
