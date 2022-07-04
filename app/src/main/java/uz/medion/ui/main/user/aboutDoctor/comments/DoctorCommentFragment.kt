package uz.medion.ui.main.user.aboutDoctor.comments

import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.constants.Keys
import uz.medion.data.model.CommentRequest
import uz.medion.data.model.remote.Status
import uz.medion.databinding.FragmentDoctorCommentBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.ui.main.user.aboutDoctor.AboutDoctorVM
import uz.medion.utils.DateTimeUtils
import uz.medion.utils.invisible
import uz.medion.utils.visible
import java.util.*

class DoctorCommentFragment : BaseFragment<FragmentDoctorCommentBinding, AboutDoctorVM>() {

    private lateinit var aboutDoctorCommentAdapter: AboutDoctorCommentAdapter
    private var doctorId: Int = 0
    private var rating: Int = 1
    private var date = Date()
    private var time: String = "hh:mm dd.mm.yyyy"

    override fun onBound() {
        loadComment()
        giveAStar()
    }

    private fun loadComment() {
        //get from bundle Doctor ID
        val args = arguments
        if (args != null) {
            doctorId = args.getInt(Keys.BUNDLE_ABOUT_DOCTOR_ID)
        }

        //initializing comment rv
        aboutDoctorCommentAdapter = AboutDoctorCommentAdapter { }
        binding.rvComments.adapter = aboutDoctorCommentAdapter
        binding.rvComments.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        //get comment from backend
        vm.getComments(doctorId).observe(this) { comments ->
            when (comments.status) {
                Status.LOADING -> {
                    binding.progress.visible()
                }
                Status.SUCCESS -> {
                    binding.progress.invisible()
                    binding.rvComments.visible()
                    aboutDoctorCommentAdapter.setData(comments.data!!)
                }
                Status.ERROR -> {
                    binding.progress.invisible()
                }
            }
        }

        //sending comment
        binding.tvCommentSend.setOnClickListener {
            date.time = System.currentTimeMillis()
            time = DateTimeUtils.dateToText(date, "HH:mm yyyy-MM-dd").toString()
            if (binding.etUserComment.text.isNullOrEmpty() || binding.etUserComment.text.isNullOrBlank()) {
                binding.etUserComment.error = "Write comment please!"
            } else {
                vm.postComment(
                    CommentRequest(rating, doctorId, binding.etUserComment.text.toString())
                ).observe(this) { comments ->
                    when (comments.status) {
                        Status.LOADING -> {
                            binding.progress.visible()
                        }
                        Status.SUCCESS -> {
                            binding.progress.invisible()
                            binding.rvComments.visible()
                            aboutDoctorCommentAdapter.setData(comments.data!!)
                        }
                        Status.ERROR -> {
                            binding.progress.invisible()
                        }
                    }
                }
                reloadLayout()
            }
        }

        //undo comment
        binding.tvCommentUndo.setOnClickListener {
            reloadLayout()
        }
    }

    private fun giveAStar() {
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
            rating = 1
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
            rating = 2
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
            rating = 3
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
            rating = 4
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
            rating = 5
        }
    }

    private fun reloadLayout() {
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
        rating = 1
    }


    override fun getLayoutResId() = R.layout.fragment_doctor_comment
    override val vm: AboutDoctorVM
        get() = ViewModelProvider(this)[AboutDoctorVM::class.java]

}