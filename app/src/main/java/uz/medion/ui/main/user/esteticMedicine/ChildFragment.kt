package uz.medion.ui.main.user.esteticMedicine

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import uz.medion.R
import uz.medion.databinding.FragmentChildBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.utils.ImageDownloader

class ChildFragment : BaseFragment<FragmentChildBinding, EsteticMedicineVM>() {

    override fun onBound() {
        setUpViews()
    }


    private fun setUpViews() {
        ImageDownloader.loadImage(context, data!![position].imageResponses[0].url, binding.ivMain)
        binding.tvMain.text = data!![position].text
    }



    override fun getLayoutResId() = R.layout.fragment_child

    override val vm: EsteticMedicineVM
        get() = ViewModelProvider(this)[EsteticMedicineVM::class.java]

}