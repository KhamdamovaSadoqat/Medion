package uz.medion.ui.main.user.myDocuments

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.model.MyDoctorDocumentItem
import uz.medion.databinding.ItemMyDocumentsBinding

class MyDocumentsAdapter: RecyclerView.Adapter<MyDocumentsAdapter.VH>() {

    private var listItem = listOf<MyDoctorDocumentItem>()

    fun setData(listItem: List<MyDoctorDocumentItem>) {
        this.listItem = listItem
        notifyDataSetChanged()
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VH {
        val inflater = LayoutInflater.from(parent.context)
        val binding =
            DataBindingUtil.inflate<ItemMyDocumentsBinding>(
                inflater,
                R.layout.item_my_documents,
                parent,
                false
            )
        return VH(binding, parent.context)
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.onBind(listItem[position])
    }

    override fun getItemCount() = listItem.size

    class VH(private val binding: ItemMyDocumentsBinding, private val context: Context) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(date: MyDoctorDocumentItem) {
            binding.apply {
                tvDate.text = date.date
                tvResult.text = date.fileName
                tvAnalyzeType.text = date.itemType
                when(date.resultType){
                    "word" -> ivDocType.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.word))
                    "pdf" -> ivDocType.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.pdf))
                    "exel" -> ivDocType.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.exel))
                    "jpeg" -> ivDocType.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.jpeg))
                }

            }

        }
    }
}