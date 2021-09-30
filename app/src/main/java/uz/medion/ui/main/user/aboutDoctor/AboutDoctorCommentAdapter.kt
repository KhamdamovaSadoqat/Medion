package uz.medion.ui.main.user.aboutDoctor

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.model.AboutDoctorCommentItem
import uz.medion.databinding.ItemCommentOthersBinding

class AboutDoctorCommentAdapter(private val itemClickListener: (Int) -> Unit) :
    RecyclerView.Adapter<AboutDoctorCommentAdapter.VH>() {

    private var listItem = listOf<AboutDoctorCommentItem>()

    fun setData(listItem: List<AboutDoctorCommentItem>) {
        this.listItem = listItem
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemCommentOthersBinding>(
                inflater,
                R.layout.item_comment_others,
                parent,
                false
            )
        return VH(binding, parent.context)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.setOnClickListener {
            itemClickListener.invoke(position)
        }
        holder.onBind(listItem[position])
    }

    override fun getItemCount() = listItem.size
    class VH(private val binding: ItemCommentOthersBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(comment: AboutDoctorCommentItem) {
            binding.apply {
                tvComment.text = context.getText(comment.comment)
                tvDate.text = comment.date
            }

        }
    }

}