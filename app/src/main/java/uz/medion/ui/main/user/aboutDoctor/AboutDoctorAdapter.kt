package uz.medion.ui.main.user.aboutDoctor

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.model.AboutDoctorItems
import uz.medion.databinding.ItemDoctorCategoryBinding

class AboutDoctorAdapter(private val itemClickListener: (Int, Int) -> Unit) :
    RecyclerView.Adapter<AboutDoctorAdapter.VH>() {

    private var listItem = listOf<AboutDoctorItems>()
    private var lastClickedPosition: Int = 0

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listItem: List<AboutDoctorItems>) {
        this.listItem = listItem
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemDoctorCategoryBinding>(
                inflater,
                R.layout.item_doctor_category,
                parent,
                false
            )
        return VH(binding, parent.context)
    }

    override fun onBindViewHolder(holder: VH, @SuppressLint("RecyclerView") position: Int) {
        holder.itemView.setOnClickListener {
            itemClickListener.invoke(position, lastClickedPosition)
            lastClickedPosition = position
        }
        holder.onBind(listItem[position])
    }



    override fun getItemCount() = listItem.size

    class VH(private val binding: ItemDoctorCategoryBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(category: AboutDoctorItems) {
            binding.apply {
                tvCategory.setText(category.categoryName)
                tvCategory.setTextColor(ContextCompat.getColor(context, category.textColor))
                cvCard.setCardBackgroundColor(ContextCompat.getColor(context, category.backgroundColor))
            }

        }
    }


}