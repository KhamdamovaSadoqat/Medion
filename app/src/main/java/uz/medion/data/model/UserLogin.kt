package uz.medion.data.model

import com.google.gson.annotations.SerializedName

data class UserLogin(

	@field:SerializedName("accessToken")
	val accessToken: String? = null,

	@field:SerializedName("refreshToken")
	val refreshToken: String? = null
)
