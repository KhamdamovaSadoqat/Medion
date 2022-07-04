package uz.medion.ui.main.user.aboutDoctor

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.model.AppointmentTimeItemIsClicked
import uz.medion.data.model.MonthlyTimeResponse
import uz.medion.databinding.ItemAppointmentTimeBinding
import uz.medion.utils.DateTimeUtils

class AppointmentTimeAdapter(private val itemClickListener: (MonthlyTimeResponse) -> Unit) :
    RecyclerView.Adapter<AppointmentTimeAdapter.VH>() {

    private var listItem = listOf<MonthlyTimeResponse>()
    private var clickingItems = listOf<AppointmentTimeItemIsClicked>()
    private var selectedItemIndex = -1

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listItem: List<MonthlyTimeResponse>, clickingItemIsClickeds: List<AppointmentTimeItemIsClicked>) {
        this.listItem = listItem
        this.clickingItems = clickingItemIsClickeds
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemAppointmentTimeBinding>(
                inflater,
                R.layout.item_appointment_time,
                parent,
                false
            )
        return VH(binding, parent.context)
    }

    override fun onBindViewHolder(holder: VH, @SuppressLint("RecyclerView") position: Int) {
        val clickingItem = clickingItems[position]
        if (clickingItem.isSelected) {
            holder.clicked()
        } else {
            holder.notCLicked()
        }
        holder.itemView.setOnClickListener {
            itemClickListener.invoke(listItem[position])
            clickingItem.isSelected = true
            if (selectedItemIndex != -1)
                clickingItems[selectedItemIndex].isSelected = false
            selectedItemIndex = position
            notifyDataSetChanged()
        }
        holder.onBind(listItem[position])
    }

    override fun getItemCount() = listItem.size

    class VH(private val binding: ItemAppointmentTimeBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(time: MonthlyTimeResponse) {
            binding.apply {
                tvTime.text = time.localTime?.let { DateTimeUtils.textToTextDate(it) }
            }
        }

        fun clicked(){
            binding.apply {
                tvTime.setTextColor(ContextCompat.getColor(context, R.color.white))
                tvTime.background = ContextCompat.getDrawable(context, R.drawable.bg_blue_lighter_8)
            }
        }

        fun notCLicked(){
            binding.apply {
                tvTime.setTextColor(ContextCompat.getColor(context, R.color.tangaroa_900))
                tvTime.background = ContextCompat.getDrawable(context, R.drawable.bg_transparent_4)
            }
        }
    }
}
