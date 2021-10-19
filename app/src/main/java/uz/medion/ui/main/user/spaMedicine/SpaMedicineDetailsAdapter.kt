package uz.medion.ui.main.user.spaMedicine

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.databinding.ItemSpaDetailsBinding

class SpaMedicineDetailsAdapter (private val itemClickListener: (Int) -> Unit) :
    RecyclerView.Adapter<SpaMedicineDetailsAdapter.VH>() {

    private var listItem = listOf<Int>()
    private var listItemDetails = listOf<Int>()


    fun setData(listItem: List<Int>, listItemDetails: List<Int>) {
        this.listItem = listItem
        this.listItemDetails = listItemDetails
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemSpaDetailsBinding>(
                inflater,
                R.layout.item_spa_details,
                parent,
                false
            )
        return VH(binding, parent.context)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.setOnClickListener {
            itemClickListener.invoke(position)
        }
        holder.onBind(listItem[position], listItemDetails[position])
    }

    override fun getItemCount() = listItem.size

    class VH(private val binding: ItemSpaDetailsBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(header: Int, description: Int) {
            binding.apply {
                tvHeader.text = context.getText(header)
                tvShortDiscription.text  = context.getText(description)
            }
        }
    }
}