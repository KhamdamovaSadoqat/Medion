package uz.medion.ui.main.user.aboutDoctor

import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.databinding.FragmentAboutDoctorBinding
import uz.medion.ui.base.BaseFragment


class AboutDoctorFragment : BaseFragment<FragmentAboutDoctorBinding, AboutDoctorVM>() {

    override fun onBound() {
        setUp()

    }

    fun setUp() {
        val ytPlayer = binding.ytAboutCenter
        ytPlayer.initialize(
            Constants.YOUTUBE_API,
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider,
                    youTubePlayer: YouTubePlayer, b: Boolean
                ) {
                    youTubePlayer.loadVideo("https://www.youtube.com/watch?v=xShKLgg4vvc")
                    youTubePlayer.play()
                }

                override fun onInitializationFailure(
                    provider: YouTubePlayer.Provider,
                    youTubeInitializationResult: YouTubeInitializationResult
                ) {
                    Toast.makeText(
                        requireContext(), "something went wrong", Toast.LENGTH_SHORT
                    ).show()
                }
            })

    }

    override fun getLayoutResId() = R.layout.fragment_our_doctors
    override val vm: AboutDoctorVM
        get() = ViewModelProvider(this).get(AboutDoctorVM::class.java)

}