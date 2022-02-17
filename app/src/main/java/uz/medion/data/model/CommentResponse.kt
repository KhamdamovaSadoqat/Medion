package uz.medion.data.model

import com.google.gson.annotations.SerializedName

data class CommentResponse(

	@field:SerializedName("score")
	val score: Int? = null,

	@field:SerializedName("author")
	val author: String? = null,

	@field:SerializedName("comment")
	val comment: String? = null
)
