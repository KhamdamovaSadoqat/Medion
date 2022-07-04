package uz.medion.data.model

import com.google.gson.annotations.SerializedName

data class ChatMessagesResponse(

	@field:SerializedName("createdAt")
	val createdAt: String? = null,

	@field:SerializedName("senderId")
	val senderId: Int? = null,

	@field:SerializedName("senderName")
	val senderName: String? = null,

	@field:SerializedName("chatId")
	val chatId: Int? = null,

	@field:SerializedName("recipientId")
	val recipientId: Int? = null,

	@field:SerializedName("recipientName")
	val recipientName: String? = null,

	@field:SerializedName("state")
	val state: String? = null,

	@field:SerializedName("text")
	val text: String? = null,

	@field:SerializedName("readAt")
	val readAt: String? = null
)
