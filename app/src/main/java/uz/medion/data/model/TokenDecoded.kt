package uz.medion.data.model

import com.google.gson.annotations.SerializedName

data class TokenDecoded(

	@field:SerializedName("sub")
	val sub: String,

	@field:SerializedName("roles")
	val roles: List<String>,

	@field:SerializedName("exp")
	val exp: Int,

	@field:SerializedName("userId")
	val userId: Int,

	@field:SerializedName("iat")
	val iat: Int,

	@field:SerializedName("jti")
	val jti: String
)
