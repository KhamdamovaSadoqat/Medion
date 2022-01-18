package uz.medion.ui.main.user.adressAndContacts

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.model.AdressAndContactsItem
import uz.medion.data.model.BranchResponse
import uz.medion.databinding.ItemLocationsBinding
import uz.medion.utils.ImageDownloader

class AddressAndContactsAdapter(private val itemClickListener: (BranchResponse) -> Unit) :
RecyclerView.Adapter<AddressAndContactsAdapter.VH>() {

    private var listItem = listOf<BranchResponse>()
    fun setData(listItem: List<BranchResponse>) {
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
            itemClickListener.invoke(listItem[position])
        }
        holder.onBind(listItem[position])
    }

    override fun getItemCount() = listItem.size

    class VH(private val binding: ItemLocationsBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: BranchResponse) {
            binding.apply {
                ImageDownloader.loadImage(context, item.imgUrl!!, binding.ivCenterPhoto)
                tvClinicName.text = item.title
                tvLocation.text = item.address
                tvMobilePhone.text = item.phone
            }
        }
    }
}