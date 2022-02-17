package uz.medion.ui.main.user.ourDoctors

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.model.SpecialityItemResponse
import uz.medion.databinding.ItemDoctorCategoryBinding

class OurDoctorsCategoryAdapter(private val itemClickListener: (Int) -> Unit) :
    RecyclerView.Adapter<OurDoctorsCategoryAdapter.VH>() {

    private var listItem = listOf<SpecialityItemResponse>()

    fun setData(listItem: List<SpecialityItemResponse>) {
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

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.setOnClickListener {
            itemClickListener.invoke(position+1)
        }
        holder.onBind(listItem[position])
    }

    override fun getItemCount() = listItem.size
    class VH(private val binding: ItemDoctorCategoryBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(category: SpecialityItemResponse) {
            binding.apply {
                tvCategory.text = category.name
                cvCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.solitude_50))
            }
        }
    }
}