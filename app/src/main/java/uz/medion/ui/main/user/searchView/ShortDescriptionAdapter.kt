package uz.medion.ui.main.user.searchView

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.databinding.ItemSearchViewBinding

class ShortDescriptionAdapter(private var itemCliCklistener:(Int)->Unit)
    : RecyclerView.Adapter<ShortDescriptionAdapter.DescriptionViewHolder>() {

    private var arrayList= listOf<Int>()

    fun setData(lists: ArrayList<Int>){
        this.arrayList=lists
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DescriptionViewHolder {
        val inflater=LayoutInflater.from(parent.context)
        val binding=DataBindingUtil.inflate<ItemSearchViewBinding>(inflater, R.layout.item_search_view,parent,false)
        return DescriptionViewHolder(binding,parent.context)
    }


    override fun onBindViewHolder(holder: DescriptionViewHolder, position: Int) {
        holder.itemView.setOnClickListener {
            itemCliCklistener.invoke(position)
        }
        holder.onBind(arrayList[position])
    }

    override fun getItemCount()=arrayList.size

    class DescriptionViewHolder(private var binding: ItemSearchViewBinding,val context:Context)
        :RecyclerView.ViewHolder(binding.root){
            fun onBind(category: Int){
                binding.apply {
                    tvNameGinekalogiya.text=context.getText(category)
                }
            }
    }
}