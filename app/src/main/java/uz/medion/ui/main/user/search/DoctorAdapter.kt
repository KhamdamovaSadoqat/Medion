package uz.medion.ui.main.user.search

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.model.DoctorsItem
import uz.medion.databinding.ItemDoctorSearchBinding

class DoctorAdapter(private var itemClickListener: (Int) -> Unit) :
    RecyclerView.Adapter<DoctorAdapter.DoctorViewHolder>() {

    private var arrayList = arrayListOf<DoctorsItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun setData(lists: ArrayList<DoctorsItem>) {
        this.arrayList = lists
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DoctorViewHolder {

        val inflater = LayoutInflater.from(parent.context)
        val binding = DataBindingUtil.inflate<ItemDoctorSearchBinding>(
            inflater,
            R.layout.item_doctor_search,
            parent,
            false
        )
        return DoctorViewHolder(binding,parent.context)
    }

    override fun onBindViewHolder(holder: DoctorViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
             itemClickListener.invoke(position)
        }
        holder.onBind(arrayList[position])
    }

    override fun getItemCount() = arrayList.size

    class DoctorViewHolder(private var binding: ItemDoctorSearchBinding, val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(category: DoctorsItem) {
            binding.apply {
                cardProfileAvater.setImageDrawable(
                    ContextCompat.getDrawable(
                        context,
                       category.image
                    )
                )
                tvFullName.text = context.getString(category.name)
                tvCategoryOfDoctor.text = context.getString(category.job)
            }
        }
    }
}