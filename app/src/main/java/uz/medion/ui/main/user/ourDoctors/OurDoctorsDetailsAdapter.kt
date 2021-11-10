package uz.medion.ui.main.user.ourDoctors

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.model.DoctorDetailItem
import uz.medion.data.model.HomeItem
import uz.medion.databinding.ItemDoctorDetailsBinding
import uz.medion.databinding.ItemHomeBinding
import uz.medion.ui.main.user.home.HomeAdapter

class OurDoctorsDetailsAdapter(private val itemClickListener: (DoctorDetailItem) -> Unit) :
    RecyclerView.Adapter<OurDoctorsDetailsAdapter.VH>() {

    private var listItem = listOf<DoctorDetailItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listItem: List<DoctorDetailItem>) {
        this.listItem = listItem
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): VH {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemDoctorDetailsBinding>(
                inflater,
                R.layout.item_doctor_details,
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

    class VH(private val binding: ItemDoctorDetailsBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(doctorDetailItem: DoctorDetailItem) {
            binding.apply {
                tvFullName.text = context.getString(doctorDetailItem.doctorName)
                tvCategoryOfDoctor.text = context.getString(doctorDetailItem.doctorCategory)
//                tvExperience.text = context.getString(doctorDetailItem.experience)
//                tvComments.text = context.getString(doctorDetailItem.comment)
//                tvClinicName.text = context.getString(doctorDetailItem.clinicName)
//                tvDetail.text = context.getString(doctorDetailItem.details)
            }
        }
    }

}