package uz.medion.ui.main.doctor.myPatient

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.model.doctor.DoctorMyPacientesResponseItem
import uz.medion.databinding.ItemSelectedBinding

class MyPatientAdapter(private val listener: PatientItemListener) :
    RecyclerView.Adapter<MyPatientAdapter.VH>() {


    private var list = listOf<DoctorMyPacientesResponseItem>()

    interface PatientItemListener {
        fun onClickPatient(patient:DoctorMyPacientesResponseItem)
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<DoctorMyPacientesResponseItem>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemSelectedBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_selected, parent, false)
        return VH(binding, parent.context)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.setOnClickListener {
            listener.onClickPatient(list[position])
        }
        holder.onBind(list[position])

    }

    override fun getItemCount(): Int = list.size
    class VH(private val binding: ItemSelectedBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(patient: DoctorMyPacientesResponseItem) {
            binding.patient=patient


                if (patient.isFavorite)
                   binding.icFavouritePatient.setImageDrawable(
                        ContextCompat.getDrawable(
                            context, R.drawable.ic_heart_sign
                        )
                    )
                else binding.icFavouritePatient.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_heart_unsign
                    )
                )


        }
    }
}