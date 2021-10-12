package uz.medion.ui.main.user.myDoctors

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.model.MyDoctorsItem
import uz.medion.databinding.ItemMyDoctorsBinding

class MyDoctorsAdapter(private val itemClickListener: (Int) -> Unit) :
    RecyclerView.Adapter<MyDoctorsAdapter.VH>() {

    private var listItem = listOf<MyDoctorsItem>()

    fun setData(listItem: List<MyDoctorsItem>) {
        this.listItem = listItem
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemMyDoctorsBinding>(
                inflater,
                R.layout.item_my_doctors,
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

    class VH(private val binding: ItemMyDoctorsBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(category: MyDoctorsItem) {
            binding.apply {
                tvDoctorName.text = context.getString(category.Name)
                tvDoctorSphere.text = context.getString(category.CategoryName)
                if (category.isFavourite) ivFavourites.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_heart_sign
                    )
                )
                else ivFavourites.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_heart_unsign
                    )
                )
            }

        }
    }
}