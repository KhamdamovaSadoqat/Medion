package uz.medion.ui.main.user.aboutDoctor

import android.util.Log
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.data.model.AboutDoctorItems
import uz.medion.databinding.FragmentAboutDoctorBinding
import uz.medion.databinding.ItemDoctorCategoryBinding
import uz.medion.ui.base.BaseFragment

class AboutDoctorFragment : BaseFragment<FragmentAboutDoctorBinding, AboutDoctorVM>() {

    private lateinit var aboutDoctorItemAdapter: AboutDoctorAdapter
    private lateinit var adapterBinding: ItemDoctorCategoryBinding

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

        adapterBinding = DataBindingUtil.inflate(
            layoutInflater, R.layout.item_doctor_category,
            this.view as ViewGroup?, false
        )

//        binding.cvCard.setCardBackgroundColor(ContextCompat.getColor(context, R.color.nile_blue_900))
//        binding.tvCategory.setTextColor(ContextCompat.getColor(context, R.color.white))

        aboutDoctorItemAdapter = AboutDoctorAdapter {
            Log.d("-------------", "setUp: $it")
            when (it) {
                0 -> {
                    adapterBinding.cvCard.setCardBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.nile_blue_900
                        )
                    )
                    adapterBinding.tvCategory.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.white
                        )
                    )
                }
                1 -> {
                    adapterBinding.cvCard.setCardBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.nile_blue_900
                        )
                    )
                    adapterBinding.tvCategory.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.white
                        )
                    )
                }
                2 -> {
                    adapterBinding.cvCard.setCardBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.nile_blue_900
                        )
                    )
                    adapterBinding.tvCategory.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.white
                        )
                    )
                }
                3 -> {
                    adapterBinding.cvCard.setCardBackgroundColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.nile_blue_900
                        )
                    )
                    adapterBinding.tvCategory.setTextColor(
                        ContextCompat.getColor(
                            requireContext(),
                            R.color.white
                        )
                    )
                }
            }
        }
        aboutDoctorItemAdapter.setData(Constants.getAboutDoctorItems())
        binding.rvDoctorAboutDetails.adapter = aboutDoctorItemAdapter
        binding.rvDoctorAboutDetails.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
    }

    override fun getLayoutResId() = R.layout.fragment_about_doctor
    override val vm: AboutDoctorVM
        get() = ViewModelProvider(this).get(AboutDoctorVM::class.java)


}