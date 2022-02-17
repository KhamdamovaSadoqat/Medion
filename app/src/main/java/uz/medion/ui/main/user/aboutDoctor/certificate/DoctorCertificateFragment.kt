package uz.medion.ui.main.user.aboutDoctor.certificate

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import uz.medion.R
import uz.medion.data.model.remote.Status
import uz.medion.databinding.FragmentDoctorCertificateBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.ui.main.user.aboutDoctor.AboutDoctorFragmentDirections
import uz.medion.ui.main.user.aboutDoctor.AboutDoctorVM

class DoctorCertificateFragment : BaseFragment<FragmentDoctorCertificateBinding, AboutDoctorVM>() {

    private lateinit var aboutDoctorCertificateAdapter: AboutDoctorCertificateAdapter

    override fun onBound() {
        loadCertificate()
    }

    private fun loadCertificate() {
        //certificate
        aboutDoctorCertificateAdapter = AboutDoctorCertificateAdapter { certificateUrl ->

            //////////////////////////////////////////////////////
            ////////////////////////////////////////////////////
            /////////////////////////////////////////////////
            //////////////////////////////////////////////
            // CAUTION MIGHT CRASH HERE
            // CAUSE ANOTHER FRAGMENT DIRECTION
            // NEED TO CHECK WITH DATA

            val action = AboutDoctorFragmentDirections.actionAboutDoctorFragmentToCertificateFragment(certificateUrl)
            findNavController().navigate(action)
        }

        binding.rvCertificate.adapter = aboutDoctorCertificateAdapter
        binding.rvCertificate.layoutManager = GridLayoutManager(requireContext(), 2)

    }

    fun getCertificate(username: String) {
        vm.getCertificate(username).observe(this) { certificates ->
            when (certificates.status) {
                Status.LOADING -> {}
                Status.SUCCESS -> {
                    aboutDoctorCertificateAdapter.setData(certificates.data!!)
                }
                Status.ERROR -> {}
            }
        }
    }

    override fun getLayoutResId() = R.layout.fragment_doctor_certificate
    override val vm: AboutDoctorVM
        get() = ViewModelProvider(this)[AboutDoctorVM::class.java]

}