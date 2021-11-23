package uz.medion.ui.main.doctor.myPatient

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.model.doctor.MyPatientsItem
import uz.medion.databinding.ItemSelectedBinding

class MyPatientAdapter(private val listener: PatientItemListener) :
    RecyclerView.Adapter<MyPatientAdapter.VH>() {


    private var list = listOf<MyPatientsItem>()

    interface PatientItemListener {
        fun onClickPatient(patient: MyPatientsItem)
    }

    fun setData(list: List<MyPatientsItem>) {
        this.list = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPatientAdapter.VH {
        val inflater = LayoutInflater.from(parent.context)
        val binding: ItemSelectedBinding =
            DataBindingUtil.inflate(inflater, R.layout.item_selected, parent, false)
        return VH(binding, parent.context)
    }

    override fun onBindViewHolder(holder: MyPatientAdapter.VH, position: Int) {
        holder.itemView.setOnClickListener {
            listener.onClickPatient(list[position])
        }
        holder.onBind(list[position])

    }

    override fun getItemCount(): Int = list.size
    class VH(private val binding: ItemSelectedBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(patient: MyPatientsItem) {

            binding.apply {
                tvNamePatient.text = context.getString(patient.Name)
                tvDatePatient.text = context.getString(patient.date)
                if (patient.isFavourite)
                    icFavouritePatient.setImageDrawable(
                        ContextCompat.getDrawable(
                            context, R.drawable.ic_heart_sign
                        )
                    )
                else icFavouritePatient.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                        R.drawable.ic_heart_unsign
                    )
                )
            }

        }
    }
}