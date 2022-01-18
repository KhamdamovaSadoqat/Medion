package uz.medion.ui.main.user.myDoctors

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.model.DoctorResponse
import uz.medion.databinding.ItemMyDoctorsBinding

class MyDoctorsAdapter(private val itemClickListener: (DoctorResponse) -> Unit) :
    RecyclerView.Adapter<MyDoctorsAdapter.VH>(), View.OnClickListener {

    private var listItem = listOf<DoctorResponse>()
    private var position = 0

    @SuppressLint("NotifyDataSetChanged")
    fun setData(listItem: List<DoctorResponse>) {
        this.listItem = listItem
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemMyDoctorsBinding>(
                inflater,
                R.layout.item_my_doctors,
                parent,
                false
            )
        return VH(binding, parent.context)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        this.position = holder.adapterPosition
        holder.itemView.setOnClickListener {
            itemClickListener.invoke(listItem[position])
        }
        holder.onBind(listItem[position])
    }

    override fun getItemCount() = listItem.size

    class VH(private val binding: ItemMyDoctorsBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(category: DoctorResponse) {
            binding.apply {
                tvDoctorName.text = category.username
                tvDoctorSphere.text = category.workInfoList[0].position
//                if (category.isFavourite) ivFavourites.setImageDrawable(
//                    ContextCompat.getDrawable(
//                        context,
//                        R.drawable.ic_heart_sign
//                    )
//                )
//                else ivFavourites.setImageDrawable(
//                    ContextCompat.getDrawable(
//                        context,
//                        R.drawable.ic_heart_unsign
//                    )
//                )
            }

        }
    }

    override fun onClick(v: View?) {
        when(v!!.id){
            R.id.iv_favourites -> {
                itemClickListener.invoke(listItem[position])
            }
        }
    }
}