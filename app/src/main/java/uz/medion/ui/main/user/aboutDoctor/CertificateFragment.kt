package uz.medion.ui.main.user.aboutDoctor

import androidx.lifecycle.ViewModelProvider
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.data.constants.Keys
import uz.medion.databinding.FragmentCertificateBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.utils.ImageDownloader
import uz.medion.utils.ZoomIn

class CertificateFragment : BaseFragment<FragmentCertificateBinding, AboutDoctorVM>() {

    private var certificatePosition: Int = 0

    override fun onBound() {
        certificatePosition = requireArguments().getInt(Keys.BUNDLE_CERTIFICATE, 0)
        ImageDownloader.loadImage(
            requireContext(),
            Constants.getSertificate()[certificatePosition].image,
            binding.ivCertificate
        )

    }

    override fun getLayoutResId() = R.layout.fragment_certificate
    override val vm: AboutDoctorVM
        get() = ViewModelProvider(this).get(AboutDoctorVM::class.java)

}