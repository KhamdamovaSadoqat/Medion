package uz.medion.data.model

import com.google.gson.annotations.SerializedName

data class MessageRequest(

	@field:SerializedName("recipientId")
	val recipientId: Int,

	@field:SerializedName("text")
	val text: String
)
