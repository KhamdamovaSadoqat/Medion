package uz.medion.ui.main.user.aboutDoctor.details

import androidx.annotation.NonNull
import androidx.lifecycle.ViewModelProvider
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import uz.medion.R
import uz.medion.data.constants.Keys
import uz.medion.databinding.FragmentDoctorDetailsBinding
import uz.medion.ui.base.BaseFragment
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import uz.medion.ui.main.user.aboutDoctor.AboutDoctorVM


class DoctorDetailsFragment : BaseFragment<FragmentDoctorDetailsBinding, AboutDoctorVM>() {

    private var youtubeUrl = ""

    override fun onBound() {
        val args = arguments
        if (args != null) {
            binding.tvAboutConsultation.text = args.getString(Keys.BUNDLE_ABOUT_DOCTOR)
            binding.tvEducation.text = args.getString(Keys.BUNDLE_ABOUT_DOCTOR_FACULTY)
            binding.tvGraduation.text = args.getString(Keys.BUNDLE_ABOUT_DOCTOR_UNIVERSITY)
            youtubeUrl = args.getString(Keys.BUNDLE_ABOUT_DOCTOR_URL)!!

        }
        lifecycle.addObserver(binding.youtubePlayerView)
        //giving youtube id for streaming
        val youtubeId = youtubeUrl.split("=")
        binding.youtubePlayerView.addYouTubePlayerListener(object :
            AbstractYouTubePlayerListener() {
            override fun onReady(@NonNull youTubePlayer: YouTubePlayer) {
                val videoId = youtubeId[youtubeId.size-1]
                youTubePlayer.loadVideo(videoId, 0F)
            }
        })
    }

    override fun getLayoutResId() = R.layout.fragment_doctor_details
    override val vm: AboutDoctorVM
        get() = ViewModelProvider(this).get(AboutDoctorVM::class.java)

}