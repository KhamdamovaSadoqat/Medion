package uz.medion.ui.main.user.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.model.HomeItem
import uz.medion.databinding.ItemHomeBinding

class HomeAdapter(private val itemClickListener: (Int) -> Unit) :
    RecyclerView.Adapter<HomeAdapter.VH>() {

    private var listItem = listOf<HomeItem>()

    fun setData(listItem: List<HomeItem>) {
        this.listItem = listItem
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemHomeBinding>(inflater, R.layout.item_home, parent, false)
        return  VH(binding, parent.context)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.setOnClickListener {
            itemClickListener.invoke(position)
        }
        holder.onBind(listItem[position])
    }

    override fun getItemCount() = listItem.size

    class VH(private val binding: ItemHomeBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(home: HomeItem) {
            binding.apply {
                imageItem.setImageDrawable(ContextCompat.getDrawable(context, home.icon))
                textItem.setText(home.text)
            }
        }

    }


}