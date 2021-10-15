package uz.medion.ui.main.user.adressAndContacts

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.model.AdressAndContactsItem
import uz.medion.databinding.ItemLocationsBinding

class AdressAndContactsAdapter(private val itemClickListener: (Int) -> Unit) :
RecyclerView.Adapter<AdressAndContactsAdapter.VH>() {

    private var listItem = listOf<AdressAndContactsItem>()
    fun setData(listItem: List<AdressAndContactsItem>) {
        this.listItem = listItem
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemLocationsBinding>(
                inflater,
                R.layout.item_locations,
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

    class VH(private val binding: ItemLocationsBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: AdressAndContactsItem) {
            binding.apply {
                ivCenterPhoto.setImageDrawable(ContextCompat.getDrawable(context, item.image))
                tvClinicName.text = item.name
                tvLocation.text = item.location
                tvMobilePhone.text = item.phone
            }

        }
    }
}