package uz.medion.ui.main.user.chat

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.data.model.ChatMessageItem
import uz.medion.databinding.ItemReceivedMessageBinding
import uz.medion.databinding.ItemSendMessageBinding

class ChatAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>(){

    private  var chatMessage:List<ChatMessageItem> =ArrayList()
    private lateinit var senderId:String

    class SentMessageVH(
        private val binding: ItemSendMessageBinding,
        private val context: Context
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(category: ChatMessageItem) {
            binding.apply {
                tvSend.text = context.getString(category.message)
            }
        }
    }

    class ReceivedVH(private val binding: ItemReceivedMessageBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(category: ChatMessageItem) {
            binding.apply {
                tvReceive.text = context.getString(category.message)
                tvTime.text = context.getString(category.dateTime)
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
            notifyDataSetChanged()
        }else{
            (holder as ReceivedVH).onBind(chatMessage[position])
            notifyDataSetChanged()
        }
    }

    override fun getItemCount() = chatMessage.size

    override fun getItemViewType(position: Int): Int {
        return if (chatMessage[position].senderedId == senderId) {
            Constants.VIEW_TYPE_SENT
        } else {
            Constants.VIEW_TYPE_RECEIVED
        }
    }
}