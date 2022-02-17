package uz.medion.ui.main.user.aboutDoctor.work

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.model.WorkInfoListItem
import uz.medion.databinding.ItemWorkDetailsBinding

class AboutDoctorWorkAdapter: RecyclerView.Adapter<AboutDoctorWorkAdapter.VH>() {


    private var listItem = listOf<WorkInfoListItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listItem: ArrayList<WorkInfoListItem>) {
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
        fun onBind(work: WorkInfoListItem) {
            binding.apply {
                tvClinicName.text = work.organization
                tvSphere.text = work.position
            }

        }
    }
}