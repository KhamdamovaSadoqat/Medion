package uz.medion.ui.main.user.chat

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import uz.medion.data.model.MessageRequest
import uz.medion.data.model.remote.Resource
import uz.medion.data.repository.UserRepository
import uz.medion.ui.base.BaseVM

class ChatVM : BaseVM() {
    val repo = UserRepository()
    var messageResponse = MutableLiveData<Resource<Boolean>>()

    fun postChat(messageRequest: MessageRequest): LiveData<Resource<Boolean>> {
        repo.postChat(messageResponse, messageRequest)
        return messageResponse
    }
}