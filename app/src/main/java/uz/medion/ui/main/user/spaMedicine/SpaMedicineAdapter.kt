package uz.medion.ui.main.user.spaMedicine

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.databinding.ItemSpaHeaderBinding

class SpaMedicineAdapter(private val itemClickListener: (Int) -> Unit) :
    RecyclerView.Adapter<SpaMedicineAdapter.VH>() {

    private var listItem = listOf<Int>()

    fun setData(listItem: List<Int>) {
        Log.d("-------------", "setData: $listItem")
        this.listItem = listItem
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemSpaHeaderBinding>(
                inflater,
                R.layout.item_spa_header,
                parent,
                false
            )
        return VH(binding, parent.context)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.setOnClickListener {
            itemClickListener.invoke(listItem[position])
        }
        holder.onBind(listItem[position])
    }

    override fun getItemCount() = listItem.size

    class VH(private val binding: ItemSpaHeaderBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(string: Int) {
            Log.d("-------------", "onBind: string: $string")
            binding.apply {
                Log.d("-------------", "onBind: ${context.getText(string)}")
                tvHeader.text = context.getText(string)
            }
        }
    }
}