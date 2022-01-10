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
import uz.medion.data.model.DoctorResponse
import uz.medion.data.model.HomeItem
import uz.medion.databinding.ItemDoctorDetailsBinding
import uz.medion.databinding.ItemHomeBinding
import uz.medion.ui.main.user.home.HomeAdapter
import uz.medion.utils.ImageDownloader

class OurDoctorsDetailsAdapter(private val itemClickListener: (DoctorResponse) -> Unit) :
    RecyclerView.Adapter<OurDoctorsDetailsAdapter.VH>() {

    private var listItem = listOf<DoctorResponse>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listItem: List<DoctorResponse>) {
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
        fun onBind(doctorDetailItem: DoctorResponse) {
            binding.apply {
                ImageDownloader.loadImage(context, doctorDetailItem.image, sivProfilePicture)
                tvFullName.text = doctorDetailItem.fullName
                tvCategoryOfDoctor.text = doctorDetailItem.workInfoList[0].position
                tvClinicName.text = doctorDetailItem.workInfoList[0].organization
//                tvExperience.text
//                tvComments.text
//                tvDetail.text
            }
        }
    }

}