package uz.medion.ui.main.user.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.medion.data.model.ChatGrouppedResponse
import uz.medion.data.model.ChatMessagesResponse
import uz.medion.data.model.MessageRequest
import uz.medion.data.model.remote.Resource
import uz.medion.data.repository.UserRepository
import uz.medion.ui.base.BaseVM

class ChatVM : BaseVM() {
    val repo = UserRepository()
    private var messageResponse = MutableLiveData<Resource<Boolean>>()
    private var chatMessagesResponse = MutableLiveData<Resource<List<ChatMessagesResponse>>>()

    fun postChat(messageRequest: MessageRequest): LiveData<Resource<Boolean>> {
        repo.postChat(messageResponse, messageRequest)
        return messageResponse
    }

    fun getChatMessages(chatId: Int): LiveData<Resource<List<ChatMessagesResponse>>>{
        repo.getChatMessages(chatMessagesResponse, chatId)
        return chatMessagesResponse
    }
}