package uz.medion.ui.main.user.ourDoctors

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.model.DoctorResponse
import uz.medion.databinding.ItemDoctorDetailsBinding
import uz.medion.utils.ImageDownloader

class OurDoctorsDetailsAdapter(private val itemClickListener: (Int) -> Unit) :
    RecyclerView.Adapter<OurDoctorsDetailsAdapter.VH>(), View.OnClickListener {

    var listItem = listOf<DoctorResponse>()
    private var position = 0

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listItem: List<DoctorResponse>) {
        this.listItem = listItem
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
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
        this.position = holder.adapterPosition
        holder.btnDoctorAppointment.setOnClickListener(this)
        holder.itemView.setOnClickListener {
            itemClickListener.invoke(listItem[position].id!!)
        }
        holder.onBind(listItem[position])
    }

    override fun onClick(v: View?) {
       when(v!!.id){
           R.id.btn_doctor_details_appointment -> {
               itemClickListener.invoke(listItem[position].id!!)
           }
       }
    }

    override fun getItemCount() = listItem.size

    class VH(private val binding: ItemDoctorDetailsBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {

        var btnDoctorAppointment: Button = binding.btnDoctorDetailsAppointment

        fun onBind(doctorDetailItem: DoctorResponse) {
            binding.apply {
                ImageDownloader.loadImage(context, doctorDetailItem.image!!, sivProfilePicture)
                tvFullName.text = doctorDetailItem.fullName
                tvCategoryOfDoctor.text = doctorDetailItem.workInfoList!![0]!!.position
                tvClinicName.text = doctorDetailItem.workInfoList[0]!!.organization
                tvExperience.text = doctorDetailItem.workExperience
                tvComments.text = doctorDetailItem.commentCount.toString()
//                tvDetail.text
            }
        }
    }
}