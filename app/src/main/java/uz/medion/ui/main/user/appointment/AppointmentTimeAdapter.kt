package uz.medion.ui.main.user.appointment

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.model.AppointmentTimeItem
import uz.medion.databinding.ItemAppointmentTimeBinding

class AppointmentTimeAdapter(private val itemClickListener: (Int, Int) -> Unit) :
    RecyclerView.Adapter<AppointmentTimeAdapter.VH>() {

    private var listItem = listOf<AppointmentTimeItem>()
    private var lastClickedPosition: Int = 0

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listItem: List<AppointmentTimeItem>) {
        this.listItem = listItem
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
        holder.itemView.setOnClickListener {
            itemClickListener.invoke(position, lastClickedPosition)
            lastClickedPosition = position
        }
        holder.onBind(listItem[position])
    }

    override fun getItemCount() = listItem.size

    class VH(private val binding: ItemAppointmentTimeBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(appointment: AppointmentTimeItem) {
            binding.apply {
                tvTime.background = ContextCompat.getDrawable(context, appointment.background)
                tvTime.setTextColor(ContextCompat.getColor(context, appointment.textColor))
                tvTime.text = context.getText(appointment.time)
            }
        }
    }


}