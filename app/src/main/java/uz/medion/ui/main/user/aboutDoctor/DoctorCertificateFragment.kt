package uz.medion.ui.main.user.aboutDoctor

import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.data.constants.Keys
import uz.medion.databinding.FragmentDoctorCertificateBinding
import uz.medion.ui.base.BaseFragment

class DoctorCertificateFragment : BaseFragment<FragmentDoctorCertificateBinding, AboutDoctorVM>() {

    private lateinit var aboutDoctorCertificateAdapter: AboutDoctorCertificateAdapter

    override fun onBound() {
        loadCertificate()
    }

    private fun loadCertificate() {
        //certificate
        aboutDoctorCertificateAdapter = AboutDoctorCertificateAdapter {
            findNavController().navigate(
                R.id.action_aboutDoctorFragment_to_certificateFragment, bundleOf(
                    Keys.BUNDLE_CERTIFICATE to it
                )
            )
        }
        aboutDoctorCertificateAdapter.setData(Constants.getSertificate())
        binding.rvSertificate.adapter = aboutDoctorCertificateAdapter
        binding.rvSertificate.layoutManager = GridLayoutManager(requireContext(), 2)

    }

    override fun getLayoutResId() = R.layout.fragment_doctor_certificate
    override val vm: AboutDoctorVM
        get() = ViewModelProvider(this).get(AboutDoctorVM::class.java)

}