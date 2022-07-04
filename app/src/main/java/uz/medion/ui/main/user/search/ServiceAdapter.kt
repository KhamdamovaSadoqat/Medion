package uz.medion.ui.main.user.search

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.model.DoctorCategoryItem
import uz.medion.data.model.ItemSearch
import uz.medion.databinding.ItemSearchViewBinding

class ServiceAdapter(private val itemClickListener: (Int) -> Unit) :
    RecyclerView.Adapter<ServiceAdapter.ItemViewHolder>() {

    private var listItem = listOf<ItemSearch>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(mlistItem: ArrayList<ItemSearch>) {
        this.listItem = mlistItem
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemSearchViewBinding>(
            inflater,
            R.layout.item_search_view,
            parent,
            false
        )

        return ItemViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClickListener.invoke(listItem[position].id)
        }
        holder.onBind(listItem[position])
    }

    override fun getItemCount() = listItem.size

    class ItemViewHolder(private val binding: ItemSearchViewBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(category: ItemSearch) {
            binding.apply {
                tvNameSv.text = category.name
            }
        }
    }
}