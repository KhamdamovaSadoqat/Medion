package uz.medion.ui.main.user.aboutDoctor

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.model.CommentResponse
import uz.medion.databinding.ItemCommentOthersBinding

class AboutDoctorCommentAdapter(private val itemClickListener: (Int) -> Unit) :
    RecyclerView.Adapter<AboutDoctorCommentAdapter.VH>() {

    private var listItem = listOf<CommentResponse>()

    fun setData(listItem: List<CommentResponse>) {
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
        fun onBind(comment: CommentResponse) {
            binding.apply {
                tvComment.text = comment.comment
                tvDate.text = comment.author
                when (comment.score) {
                    1 -> {
                        binding.ivStar1.setImageDrawable(
                            ContextCompat.getDrawable(
                                context,
                                R.drawable.ic_star
                            )
                        )
                    }
                    2 -> {
                        binding.ivStar1.setImageDrawable(
                            ContextCompat.getDrawable(
                                context,
                                R.drawable.ic_star
                            )
                        )
                        binding.ivStar2.setImageDrawable(
                            ContextCompat.getDrawable(
                                context,
                                R.drawable.ic_star
                            )
                        )
                    }
                    3 -> {
                        binding.ivStar1.setImageDrawable(
                            ContextCompat.getDrawable(
                                context,
                                R.drawable.ic_star
                            )
                        )
                        binding.ivStar2.setImageDrawable(
                            ContextCompat.getDrawable(
                                context,
                                R.drawable.ic_star
                            )
                        )
                        binding.ivStar3.setImageDrawable(
                            ContextCompat.getDrawable(
                                context,
                                R.drawable.ic_star
                            )
                        )
                    }
                    4 -> {
                        binding.ivStar1.setImageDrawable(
                            ContextCompat.getDrawable(
                                context,
                                R.drawable.ic_star
                            )
                        )
                        binding.ivStar2.setImageDrawable(
                            ContextCompat.getDrawable(
                                context,
                                R.drawable.ic_star
                            )
                        )
                        binding.ivStar3.setImageDrawable(
                            ContextCompat.getDrawable(
                                context,
                                R.drawable.ic_star
                            )
                        )
                        binding.ivStar4.setImageDrawable(
                            ContextCompat.getDrawable(
                                context,
                                R.drawable.ic_star
                            )
                        )
                    }
                    5 -> {
                        binding.ivStar1.setImageDrawable(
                            ContextCompat.getDrawable(
                                context,
                                R.drawable.ic_star
                            )
                        )
                        binding.ivStar2.setImageDrawable(
                            ContextCompat.getDrawable(
                                context,
                                R.drawable.ic_star
                            )
                        )
                        binding.ivStar3.setImageDrawable(
                            ContextCompat.getDrawable(
                                context,
                                R.drawable.ic_star
                            )
                        )
                        binding.ivStar4.setImageDrawable(
                            ContextCompat.getDrawable(
                                context,
                                R.drawable.ic_star
                            )
                        )
                        binding.ivStar5.setImageDrawable(
                            ContextCompat.getDrawable(
                                context,
                                R.drawable.ic_star
                            )
                        )
                    }
                }
            }

        }
    }

}