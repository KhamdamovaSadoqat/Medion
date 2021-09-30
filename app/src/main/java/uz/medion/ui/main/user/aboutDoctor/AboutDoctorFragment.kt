package uz.medion.ui.main.user.aboutDoctor

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerFragment
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.data.model.AboutDoctorItems
import uz.medion.databinding.FragmentAboutDoctorBinding
import uz.medion.databinding.ItemDoctorCategoryBinding
import uz.medion.ui.base.BaseFragment

class AboutDoctorFragment : BaseFragment<FragmentAboutDoctorBinding, AboutDoctorVM>() {

    private lateinit var aboutDoctorItemAdapter: AboutDoctorAdapter
    private lateinit var adapterBinding: ItemDoctorCategoryBinding
    private lateinit var aboutDoctorCommentAdapter: AboutDoctorCommentAdapter
    private lateinit var data: ArrayList<AboutDoctorItems>

    override fun onBound() {
        setUp()
    }

    fun setUp() {
//        binding.ytAboutCenter.initialize(
//            Constants.YOUTUBE_API,
//            object : YouTubePlayer.OnInitializedListener {
//                override fun onInitializationSuccess(
//                    provider: YouTubePlayer.Provider,
//                    youTubePlayer: YouTubePlayer, b: Boolean
//                ) {
//                    youTubePlayer.loadVideo("")
//                    youTubePlayer.play()
//                }
//
//                override fun onInitializationFailure(
//                    provider: YouTubePlayer.Provider,
//                    youTubeInitializationResult: YouTubeInitializationResult
//                ) {
//                    Toast.makeText(
//                        requireContext(), "something went wrong", Toast.LENGTH_SHORT
//                    ).show()
//                }
//            })

        data = Constants.getAboutDoctorItems()
        aboutDoctorItemAdapter = AboutDoctorAdapter { position, lastPosition ->
            Log.d("-------------", "setUp: $lastPosition")
            Log.d("-------------", "setUp: $position")
            if (position != lastPosition) {
                data[position] =
                    AboutDoctorItems(
                        data[position].categoryName,
                        R.color.nile_blue_900,
                        R.color.white
                    )
                data[lastPosition] = AboutDoctorItems(
                    data[lastPosition].categoryName,
                    R.color.solitude_50,
                    R.color.tangaroa_900
                )
                aboutDoctorItemAdapter.setData(data)
                when (position) {
                    0 -> {
                        binding.clAboutDoctor.visibility = View.VISIBLE
                        binding.clComment.visibility = View.GONE
                        binding.clWork.visibility = View.GONE
                    }
                    1 -> {
                        binding.clAboutDoctor.visibility = View.GONE
                        binding.clWork.visibility = View.VISIBLE
                        binding.clComment.visibility = View.GONE
                    }
                    2 -> {
                        binding.clAboutDoctor.visibility = View.GONE
                        binding.clWork.visibility = View.GONE
                        binding.clComment.visibility = View.VISIBLE

                        aboutDoctorCommentAdapter = AboutDoctorCommentAdapter { }
                        aboutDoctorCommentAdapter.setData(Constants.getComments())
                        binding.rvComments.layoutManager =
                            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

                    }
                    3 -> {
                    }
                }

            }
        }
        aboutDoctorItemAdapter.setData(data)
        binding.rvDoctorAboutDetails.adapter = aboutDoctorItemAdapter
        binding.rvDoctorAboutDetails.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
    }

    override fun getLayoutResId() = R.layout.fragment_about_doctor
    override val vm: AboutDoctorVM
        get() = ViewModelProvider(this).get(AboutDoctorVM::class.java)


}