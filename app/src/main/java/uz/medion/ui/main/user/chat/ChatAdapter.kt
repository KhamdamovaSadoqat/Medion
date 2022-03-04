package uz.medion.ui.main.user.chat

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.data.model.ChatMessagesResponse
import uz.medion.databinding.ItemReceivedMessageBinding
import uz.medion.databinding.ItemSendMessageBinding

class ChatAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private var chatMessage: List<ChatMessagesResponse> = ArrayList()
    private var userId = 0

    fun setData(chatMessage: List<ChatMessagesResponse>) {
        this.chatMessage = chatMessage
        notifyDataSetChanged()
    }

    fun setUserId(userId: Int) {
        this.userId = userId
        notifyDataSetChanged()
    }

    class SentMessageVH(
        private val binding: ItemSendMessageBinding,
        private val context: Context,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(category: ChatMessagesResponse) {
            binding.apply {
                tvSend.text = category.text
                tvTime.text = category.createdAt
            }
        }
    }

    class ReceivedVH(private val binding: ItemReceivedMessageBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(category: ChatMessagesResponse) {
            binding.apply {
                tvReceive.text = category.text
                tvTime.text = category.createdAt
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == Constants.VIEW_TYPE_SENT) {
            val inflater = LayoutInflater.from(parent.context)
            val dataBinding = DataBindingUtil.inflate<ItemSendMessageBinding>(
                inflater,
                R.layout.item_send_message,
                parent,
                false
            )
            return SentMessageVH(dataBinding, parent.context)
        } else {
            val inflater = LayoutInflater.from(parent.context)
            val dataBinding = DataBindingUtil.inflate<ItemReceivedMessageBinding>(
                inflater,
                R.layout.item_received_message,
                parent,
                false
            )
            return ReceivedVH(dataBinding, parent.context)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == Constants.VIEW_TYPE_SENT){
            (holder as SentMessageVH).onBind(chatMessage[position])
        }else{
            (holder as ReceivedVH).onBind(chatMessage[position])
        }
    }

    override fun getItemCount() = chatMessage.size

    override fun getItemViewType(position: Int): Int {
        return if (chatMessage[position].senderId == userId) Constants.VIEW_TYPE_SENT
        else Constants.VIEW_TYPE_RECEIVED
    }
}