package uz.medion.ui.main.doctor.myPatient

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.model.doctor.DoctorMyPacientesResponseItem
import uz.medion.databinding.ItemSelectedBinding

class MyPatientAdapter(private val listener:(DoctorMyPacientesResponseItem)->Unit) :
    RecyclerView.Adapter<MyPatientAdapter.VH>(),View.OnClickListener {


    private var list = listOf<DoctorMyPacientesResponseItem>()
    private var position=0



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
        this.position= holder.adapterPosition
        holder.itemView.setOnClickListener {
            listener.invoke(list[position])
        }
        holder.onBind(list[position])

    }

    override fun getItemCount(): Int = list.size

    class VH(private val binding: ItemSelectedBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(patient: DoctorMyPacientesResponseItem) {
            binding.patient=patient
            binding.tvNamePatient.text=patient.username



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

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.iv_favourites->{
                listener.invoke(list[position])
            }
        }
    }
}