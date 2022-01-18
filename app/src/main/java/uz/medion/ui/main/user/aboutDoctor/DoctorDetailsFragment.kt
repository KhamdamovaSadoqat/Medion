package uz.medion.ui.main.user.aboutDoctor

import androidx.lifecycle.ViewModelProvider
import uz.medion.R
import uz.medion.databinding.FragmentDoctorDetailsBinding
import uz.medion.ui.base.BaseFragment

class DoctorDetailsFragment : BaseFragment<FragmentDoctorDetailsBinding, AboutDoctorVM>() {


    override fun onBound() {
        lifecycle.addObserver(binding.youtubePlayerView)
    }

    override fun getLayoutResId() = R.layout.fragment_doctor_details
    override val vm: AboutDoctorVM
        get() = ViewModelProvider(this).get(AboutDoctorVM::class.java)

}