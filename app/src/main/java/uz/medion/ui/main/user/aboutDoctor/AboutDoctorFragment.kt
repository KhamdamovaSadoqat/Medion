package uz.medion.ui.main.user.aboutDoctor

import android.util.Log
import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.constraintlayout.widget.ConstraintSet
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.core.view.get
import androidx.core.widget.NestedScrollView
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.data.constants.Keys
import uz.medion.data.model.AboutDoctorCommentItem
import uz.medion.data.model.AboutDoctorItems
import uz.medion.databinding.FragmentAboutDoctorBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.utils.DateTimeUtils
import uz.medion.utils.ViewUtils
import java.util.*

class AboutDoctorFragment : BaseFragment<FragmentAboutDoctorBinding, AboutDoctorVM>() {

    private lateinit var aboutDoctorSertificateAdapter: AboutDoctorSertificateAdapter
    private lateinit var aboutDoctorWorkCurrentAdapter: AboutDoctorWorkAdapter
    private lateinit var aboutDoctorWorkPastAdapter: AboutDoctorWorkAdapter
    private lateinit var aboutDoctorItemAdapter: AboutDoctorAdapter
    private lateinit var aboutDoctorCommentAdapter: AboutDoctorCommentAdapter
    private lateinit var data: ArrayList<AboutDoctorItems>
    private var reyting: Int = 1
    private var date = Date()
    private var time: String = "hh:mm dd.mm.yyyy"

    override fun onBound() {
        setUp()
    }

    fun setUp() {
//        setScreenDemention()
        loadAboutDoctor()
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

    }

    fun loadAboutDoctor() {
        // setting history of doctor
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

                aboutDoctorCommentAdapter = AboutDoctorCommentAdapter { }
                val dataa = Constants.getComments()
                Log.d("-------------", "setUp: size: ${dataa.size}")
                aboutDoctorCommentAdapter.setData(dataa)
                binding.rvComments.layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

                when (position) {
                    0 -> {
                        binding.clAboutDoctor.visibility = View.VISIBLE
                        binding.clComment.visibility = View.GONE
                        binding.clWork.visibility = View.GONE
                        binding.clSertificate.visibility = View.GONE
                    }
                    1 -> {
                        loadWork()
                        binding.clAboutDoctor.visibility = View.GONE
                        binding.clWork.visibility = View.VISIBLE
                        binding.clComment.visibility = View.GONE
                        binding.clSertificate.visibility = View.GONE
                    }
                    2 -> {
                        loadComment()
                        binding.clAboutDoctor.visibility = View.GONE
                        binding.clWork.visibility = View.GONE
                        binding.clComment.visibility = View.VISIBLE
                        binding.clSertificate.visibility = View.GONE
                    }
                    3 -> {
                        loadCertificate()
                        binding.clAboutDoctor.visibility = View.GONE
                        binding.clWork.visibility = View.GONE
                        binding.clComment.visibility = View.GONE
                        binding.clSertificate.visibility = View.VISIBLE

                    }
                }

            }
        }
        aboutDoctorItemAdapter.setData(data)
        binding.rvDoctorAboutDetails.adapter = aboutDoctorItemAdapter
        binding.rvDoctorAboutDetails.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
    }

    fun loadWork() {
        //workCurrent
        aboutDoctorWorkCurrentAdapter = AboutDoctorWorkAdapter()
        aboutDoctorWorkCurrentAdapter.setData(Constants.getCurrentWork())
        binding.rvWorkCurrent.adapter = aboutDoctorWorkCurrentAdapter
        binding.rvWorkCurrent.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        //workPast
        aboutDoctorWorkPastAdapter = AboutDoctorWorkAdapter()
        aboutDoctorWorkPastAdapter.setData(Constants.getPastWork())
        binding.rvWorkPast.adapter = aboutDoctorWorkCurrentAdapter
        binding.rvWorkPast.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

    }

    fun loadComment() {
        //generating comments from constant date
        aboutDoctorCommentAdapter = AboutDoctorCommentAdapter { }
        aboutDoctorCommentAdapter.setData(Constants.getComments())
        binding.rvComments.adapter = aboutDoctorCommentAdapter
        binding.rvComments.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        // rating the doctor
        binding.ivStar1.setOnClickListener {
            binding.apply {
                ivStar1.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star
                    )
                )
                ivStar2.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star_grey
                    )
                )
                ivStar3.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star_grey
                    )
                )
                ivStar4.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star_grey
                    )
                )
                ivStar5.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star_grey
                    )
                )
            }
            reyting = 1
        }
        binding.ivStar2.setOnClickListener {
            binding.apply {
                ivStar1.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star
                    )
                )
                ivStar2.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star
                    )
                )
                ivStar3.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star_grey
                    )
                )
                ivStar4.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star_grey
                    )
                )
                ivStar5.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star_grey
                    )
                )
            }
            reyting = 2
        }
        binding.ivStar3.setOnClickListener {
            binding.apply {
                ivStar1.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star
                    )
                )
                ivStar2.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star
                    )
                )
                ivStar3.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star
                    )
                )
                ivStar4.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star_grey
                    )
                )
                ivStar5.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star_grey
                    )
                )
            }
            reyting = 3
        }
        binding.ivStar4.setOnClickListener {
            binding.apply {
                ivStar1.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star
                    )
                )
                ivStar2.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star
                    )
                )
                ivStar3.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star
                    )
                )
                ivStar4.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star
                    )
                )
                ivStar5.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star_grey
                    )
                )
            }
            reyting = 4
        }
        binding.ivStar5.setOnClickListener {
            binding.apply {
                ivStar1.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star
                    )
                )
                ivStar2.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star
                    )
                )
                ivStar3.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star
                    )
                )
                ivStar4.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star
                    )
                )
                ivStar5.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_star
                    )
                )
            }
            reyting = 5
        }

        //sending comment
        binding.tvCommentSend.setOnClickListener {
            date.time = System.currentTimeMillis()
            time = DateTimeUtils.dateToText(date, "HH:mm yyyy-MM-dd").toString()
            if (binding.etUserComment.text.isNullOrEmpty() || binding.etUserComment.text.isNullOrBlank()) {
                binding.etUserComment.error = "Write comment please!"
            } else {
                vm.sendComment(
                    AboutDoctorCommentItem(
                        binding.etUserComment.text.toString(),
                        reyting,
                        time
                    )
                )
                reloadLayout()
            }
        }

        //undo comment
        binding.tvCommentUndo.setOnClickListener {
            reloadLayout()
        }

    }

    fun loadCertificate() {
        //certificate
        aboutDoctorSertificateAdapter = AboutDoctorSertificateAdapter {
            findNavController().navigate(
                R.id.action_aboutDoctorFragment_to_certificateFragment, bundleOf(
                    Keys.BUNDLE_CERTIFICATE to it
                )
            )
        }
        aboutDoctorSertificateAdapter.setData(Constants.getSertificate())
        binding.rvSertificate.adapter = aboutDoctorSertificateAdapter
        binding.rvSertificate.layoutManager = GridLayoutManager(requireContext(), 2)

    }

    fun reloadLayout() {
        binding.etUserComment.setText("")
        binding.apply {
            ivStar1.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_star_grey
                )
            )
            ivStar2.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_star_grey
                )
            )
            ivStar3.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_star_grey
                )
            )
            ivStar4.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_star_grey
                )
            )
            ivStar5.setImageDrawable(
                ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.ic_star_grey
                )
            )
        }
        reyting = 1
    }

    fun setScreenDemention() {
        val screenDimension = ViewUtils.screenDimention(requireContext())
//        val constraintLayout: ConstraintLayout.LayoutParams = binding.clMain.layoutParams as ConstraintLayout.LayoutParams
//        constraintLayout.height = screenDimension[1]
//        constraintLayout.width = screenDimension[0]
//        binding.clMain.layoutParams = constraintLayout

        val layout = ConstraintLayout.LayoutParams(
            50000,
            50000
        )

        val constraintSet1 = NestedScrollView(requireContext())
        constraintSet1.layoutParams = layout


        Log.d("-------------", "setScreenDemention: screen: $screenDimension")

//        constraintSet1.constrainWidth(R.id.cl_main, screenDimension[0])
//        constraintSet1.constrainHeight(R.id.cl_main, screenDimension[1])
//
//        constraintSet1.applyTo(binding.clMain)


//        layout.width = screenDimension[0]
//        layout.height = screenDimension[1]
//        binding.nsvMain.layoutParams = layout
    }

    override fun getLayoutResId() = R.layout.fragment_about_doctor
    override val vm: AboutDoctorVM
        get() = ViewModelProvider(this).get(AboutDoctorVM::class.java)

}