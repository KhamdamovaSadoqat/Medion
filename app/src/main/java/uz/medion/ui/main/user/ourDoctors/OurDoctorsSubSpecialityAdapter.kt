package uz.medion.ui.main.user.ourDoctors

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import uz.medion.data.model.AppointmentTimeItemIsClicked
import uz.medion.data.model.SubSpecialityResponse
import uz.medion.databinding.ItemSubCategoryBinding
import uz.medion.utils.Utils
import kotlin.properties.Delegates

class OurDoctorsSubSpecialityAdapter(private val itemClickListener: (Int) -> Unit) :
    RecyclerView.Adapter<OurDoctorsSubSpecialityAdapter.VH>(), Utils.AutoUpdatableAdapter {

    private var lastSelectedItemIndex = 0
    var items: List<SubSpecialityResponse> by Delegates.observable(emptyList()) { _, old, new ->
        autoNotify(old, new) { o, n -> o == n }
    }
    var clickingItems: List<AppointmentTimeItemIsClicked>  by Delegates.observable(emptyList()){ _, old, new ->
        autoNotify(old, new) { o, n -> o == n }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH =
        VH(
            ItemSubCategoryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ), parent.context
        )

    override fun onBindViewHolder(holder: VH, @SuppressLint("RecyclerView") position: Int) {
        holder.itemView.setOnClickListener {
            itemClickListener.invoke(holder.adapterPosition)
        }
        holder.onBind(items[position], clickingItems[position])

        val clickingItem = clickingItems[position]
        if (clickingItem.isSelected) {
            holder.clicked()
        } else {
            holder.notCLicked()
        }
        holder.itemView.setOnClickListener {
            itemClickListener.invoke(holder.adapterPosition)
            clickingItem.isSelected = true
            clickingItems[lastSelectedItemIndex].isSelected = false
            lastSelectedItemIndex = position
            notifyDataSetChanged()
        }
    }

    override fun getItemCount(): Int = items.size

    class VH(private val binding: ItemSubCategoryBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(subSpeciality: SubSpecialityResponse, clicked: AppointmentTimeItemIsClicked) {
            binding.apply {
                tvSubSpeciality.text = subSpeciality.name
                if(clicked.isSelected) ivSubSpeciality.visibility = View.VISIBLE
                else  ivSubSpeciality.visibility = View.INVISIBLE
            }
        }

        fun clicked() {
            binding.apply {
                ivSubSpeciality.visibility = View.VISIBLE
            }
        }

        fun notCLicked() {
            binding.apply {
                ivSubSpeciality.visibility = View.INVISIBLE
            }
        }
    }

}