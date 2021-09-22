package uz.medion.ui.main.user.ourDoctors

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import uz.medion.R
import uz.medion.databinding.FragmentOurDoctorsBinding
import uz.medion.ui.base.BaseFragment

class OurDoctorsFragment : BaseFragment<FragmentOurDoctorsBinding, OurDoctorsVM>() {


    override fun onBound() {
        TODO("Not yet implemented")
    }

    override fun getLayoutResId() = R.layout.fragment_our_doctors
    override val vm: OurDoctorsVM
        get() = ViewModelProvider(this).get(OurDoctorsVM::class.java)

}