package uz.medion.ui.main.user.home

import android.content.Context
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import uz.medion.data.model.HomeItem
import uz.medion.databinding.ItemHomeBinding

class HomeAdapter(private val itemClickListener: (Int) ->Unit): RecyclerView.Adapter<HomeAdapter.VH>()  {

    private var listItem = listOf<HomeItem>()

    fun setData(listItem: List<HomeItem>) {
        this.listItem = listItem
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.setOnClickListener {
            itemClickListener.invoke(position)
        }
    }

    override fun getItemCount() = listItem.size

    class VH(private val binding: ItemHomeBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {

        fun onBind(home: HomeItem) {
            binding.apply {
                }
        }

    }


}