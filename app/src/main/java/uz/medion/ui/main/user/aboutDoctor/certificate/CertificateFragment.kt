package uz.medion.ui.main.user.aboutDoctor.certificate

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import uz.medion.R
import uz.medion.databinding.FragmentCertificateBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.ui.main.user.aboutDoctor.AboutDoctorVM
import uz.medion.utils.ImageDownloader

class CertificateFragment : BaseFragment<FragmentCertificateBinding, AboutDoctorVM>() {

    val args: CertificateFragmentArgs by navArgs()

    override fun onBound() {
        ImageDownloader.loadImage(
            requireContext(),
            args.certificateUrl,
            binding.ivCertificate
        )
    }

    override fun getLayoutResId() = R.layout.fragment_certificate
    override val vm: AboutDoctorVM
        get() = ViewModelProvider(this)[AboutDoctorVM::class.java]
}