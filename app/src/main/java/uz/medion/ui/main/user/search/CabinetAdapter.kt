package uz.medion.ui.main.user.search

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.model.DoctorCategoryItem
import uz.medion.data.model.ItemSearch
import uz.medion.databinding.ItemSearchViewBinding

class CabinetAdapter(private val itemClickListener: (Int) -> Unit) :
    RecyclerView.Adapter<CabinetAdapter.CabinetViewHolder>() {

    private var lists = listOf<ItemSearch>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(mlist: ArrayList<ItemSearch>) {
        this.lists = mlist
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CabinetViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemSearchViewBinding>(
            inflater,
            R.layout.item_search_view,
            parent,
            false
        )
        return CabinetViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: CabinetViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            itemClickListener.invoke(lists[position].id)
        }
        holder.onBind(lists[position])
    }

    override fun getItemCount() = lists.size

    class CabinetViewHolder(private val binding: ItemSearchViewBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(category: ItemSearch) {
            binding.apply {
                tvNameSv.text = category.name
            }
        }
    }
}