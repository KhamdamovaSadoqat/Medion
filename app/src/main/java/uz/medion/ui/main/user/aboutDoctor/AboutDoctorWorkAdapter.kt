package uz.medion.ui.main.user.aboutDoctor

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.model.AboutDoctorWorkItem
import uz.medion.databinding.ItemWorkDetailsBinding

class AboutDoctorWorkAdapter: RecyclerView.Adapter<AboutDoctorWorkAdapter.VH>() {


    private var listItem = listOf<AboutDoctorWorkItem>()

    fun setData(listItem: List<AboutDoctorWorkItem>) {
        this.listItem = listItem
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemWorkDetailsBinding>(
                inflater,
                R.layout.item_work_details,
                parent,
                false
            )
        return VH(binding, parent.context)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(listItem[position])
    }

    override fun getItemCount() = listItem.size

    class VH(private val binding: ItemWorkDetailsBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(work: AboutDoctorWorkItem) {
            binding.apply {
                tvClinicName.text = work.clinicName
                tvSphere.text = work.sphere
            }

        }
    }
}