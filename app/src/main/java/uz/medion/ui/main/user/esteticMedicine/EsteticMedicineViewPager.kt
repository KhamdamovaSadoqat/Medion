package uz.medion.ui.main.user.esteticMedicine

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import uz.medion.data.constants.Keys

class EsteticMedicineViewPager(
    private val list: List<String>,
    fm: FragmentManager, lifecycle: Lifecycle
) : FragmentStateAdapter(fm, lifecycle) {

    override fun getItemCount(): Int {
        return list.size
    }

    override fun createFragment(position: Int): Fragment {
        val bundle = Bundle()
        val fr = ChildFragment()
        bundle.putInt(Keys.BUNDLE_ESTETIC_MEDICINE_POSITION, position)
        fr.arguments = bundle
        return fr
    }
}