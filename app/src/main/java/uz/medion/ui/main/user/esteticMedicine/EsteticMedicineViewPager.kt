package uz.medion.ui.main.user.esteticMedicine

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.google.gson.Gson
import android.os.Bundle
import android.R.attr.data

class EsteticMedicineViewPager(
    private val list: List<String>,
    fm: FragmentManager, lifecycle: Lifecycle
) : FragmentStateAdapter(fm, lifecycle) {

    var data = List<ChildResponsesItem>

    fun setData(data: List<ChildResponsesItem>){
        this.data = data
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {

        var  bundle: Bundle? = Bundle()
        var  gson: Gson? = Gson()

        bundle.putString("fragment1Container", gson.toJson(fragment1Container))
        fragment1.setArguments(bundle)


        return ChildFragment()
    }

//        val inflater = LayoutInflater.from(context)
//        val binding = DataBindingUtil.inflate<ItemEsteticMedicineBinding>(inflater,
//            R.layout.item_estetic_medicine,
//            container,
//            false)


}