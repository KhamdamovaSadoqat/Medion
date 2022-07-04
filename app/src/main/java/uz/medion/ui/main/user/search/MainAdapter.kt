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

class MainAdapter(private val itemCliCkListener: (Int) -> Unit) :
    RecyclerView.Adapter<MainAdapter.MainViewHolder>() {

    private var arrayList = listOf<ItemSearch>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(lists: ArrayList<ItemSearch>) {
        this.arrayList = lists
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemSearchViewBinding>(
            inflater,
            R.layout.item_search_view,
            parent,
            false
        )
        return MainViewHolder(binding, parent.context)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            itemCliCkListener.invoke(arrayList[position].id)
        }
        holder.onBind(arrayList[position])
    }

    override fun getItemCount() = arrayList.size

    class MainViewHolder(private var binding: ItemSearchViewBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(category: ItemSearch) {
            binding.apply {
                tvNameSv.text = category.name
            }
        }
    }
}