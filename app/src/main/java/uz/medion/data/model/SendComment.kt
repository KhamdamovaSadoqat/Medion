package uz.medion.data.model

import com.google.gson.annotations.SerializedName

data class SendComment(

	@field:SerializedName("score")
	val score: Int? = null,

	@field:SerializedName("doctorId")
	val doctorId: Int? = null,

	@field:SerializedName("comment")
	val comment: String? = null
)
