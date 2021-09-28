package uz.medion.ui.main.user.aboutDoctor

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.model.AboutDoctorItems
import uz.medion.data.model.DoctorCategoryItem
import uz.medion.databinding.ItemDoctorCategoryBinding
import uz.medion.ui.main.user.ourDoctors.OurDoctorsCategoryAdapter

class AboutDoctorAdapter(private val itemClickListener: (Int) -> Unit) :
    RecyclerView.Adapter<AboutDoctorAdapter.VH>() {

    private var listItem = listOf<AboutDoctorItems>()

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

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.setOnClickListener {
            itemClickListener.invoke(position)
        }
        holder.onBind(listItem[position], position)
    }

    override fun getItemCount() = listItem.size

    class VH(private val binding: ItemDoctorCategoryBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(category: AboutDoctorItems, position: Int) {
            Log.d("-------------", "onBind: position: $position")
            if(position == 0) {
                binding.cvCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.nile_blue_900))
                binding.tvCategory.setTextColor(ContextCompat.getColor(context, R.color.white))
            }else{
                binding.apply {
                    tvCategory.setText(category.categoryName)
                    binding.cvCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.solitude_50))
                }
            }
        }
    }
}