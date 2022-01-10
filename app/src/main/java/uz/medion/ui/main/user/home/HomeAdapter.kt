package uz.medion.ui.main.user.home

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.model.SpecialityItemResponse
import uz.medion.databinding.ItemHomeBinding
import uz.medion.utils.ImageDownloader

class HomeAdapter(private val itemClickListener: (Int) -> Unit) :
    RecyclerView.Adapter<HomeAdapter.VH>() {

    private var listItem = listOf<SpecialityItemResponse>()

    fun setData(listItemResponse: List<SpecialityItemResponse>) {
        this.listItem = listItemResponse
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

        fun onBind(home: SpecialityItemResponse) {
            binding.apply {
                ImageDownloader.loadImage(context, home.logo!!, imageItem)
                textItem.text = home.name
            }
        }

    }


}