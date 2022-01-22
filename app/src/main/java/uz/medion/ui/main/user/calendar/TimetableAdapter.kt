package uz.medion.ui.main.user.calendar

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.model.BookingResponse
import uz.medion.databinding.ItemTimeTableBinding

class TimetableAdapter(private val itemClickListener: (Int) -> Unit) :
    RecyclerView.Adapter<TimetableAdapter.ItemVH>() {

    private var arrayList = listOf<BookingResponse>()

    fun setData(listItem: List<BookingResponse>) {
        this.arrayList = listItem
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemVH {

        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemTimeTableBinding>(inflater,
            R.layout.item_time_table,
            parent,
            false)
        return ItemVH(binding)
    }

    override fun onBindViewHolder(holder: ItemVH, position: Int) {
        holder.itemView.setOnClickListener {
            itemClickListener.invoke(position)
        }
        holder.onBind(arrayList[position])
    }

    override fun getItemCount() = arrayList.size

    class ItemVH(private val binding: ItemTimeTableBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(booking: BookingResponse) {
            binding.apply {
                tvTime.text = booking.bookDay
                tvDetail.text = booking.doctorName
//                tvSection.text
            }
        }
    }
}