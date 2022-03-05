package uz.medion.data.model

import com.google.gson.annotations.SerializedName

data class ChatGrouppedResponse(

	@field:SerializedName("chatId")
	val chatId: Int? = null,

	@field:SerializedName("clientName")
	val clientName: String? = null,

	@field:SerializedName("lastUpdatedAt")
	val lastUpdatedAt: String? = null,

	@field:SerializedName("countOfNewMessages")
	val countOfNewMessages: Int? = null
)
