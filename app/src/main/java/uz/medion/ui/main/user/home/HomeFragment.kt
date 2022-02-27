package uz.medion.ui.main.user.home

import android.util.Log
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.flexbox.FlexDirection.ROW
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import uz.medion.R
import uz.medion.data.model.remote.Status
import uz.medion.databinding.FragmentHomeBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.utils.ViewUtils
import uz.medion.utils.invisible
import uz.medion.utils.visible

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeVM>() {

    private lateinit var adapter: HomeAdapter
    private lateinit var animationFab: Animation
    private var tvAll: Boolean = true
    private lateinit var viewPagerAdapter: ViewPagerAdapter

    override fun onBound() {
        setUp()
        setUpUI()
    }

    fun setUp() {
        vm.getSpeciality().observe(this) { speciality ->
            when (speciality.status) {
                Status.LOADING -> {
                    binding.progress.visible()
                }
                Status.SUCCESS -> {
                    binding.progress.invisible()

                    //initializing adapters
                    adapter = HomeAdapter { id ->
                        // id = it +1
                        val action =
                            HomeFragmentDirections.actionHomeFragmentToOurDoctorsFragment(id + 1)
                        findNavController().navigate(action)
                    }
                    binding.rvCategories.layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                    adapter.setData(speciality.data!!)
                    binding.rvCategories.adapter = adapter
                }
                Status.ERROR -> {
                    binding.progress.invisible()
                    Log.e("----------", "error: ${speciality.message}")
                }
            }
        }

        vm.getAboutClinic().observe(this) { aboutClinic ->
            when (aboutClinic.status) {
                Status.LOADING -> {
                    binding.progress.visible()
                }
                Status.SUCCESS -> {
                    binding.progress.invisible()
                    binding.vpPictures.visible()
                    binding.tvAboutOurCenter.visible()
                    binding.tvAboutOurCenterInfo.visible()
                    binding.dotsIndicator.visible()
                    // viewPager
                    viewPagerAdapter = ViewPagerAdapter(aboutClinic.data!!.urls, requireContext())
                    binding.vpPictures.adapter = viewPagerAdapter

                    val dotsIndicator = binding.dotsIndicator
                    dotsIndicator.setViewPager(binding.vpPictures)
                    // text : about our center
                    binding.tvAboutOurCenterInfo.text = aboutClinic.data.context
                    binding.tvAboutOurCenter.text = aboutClinic.data.title

                }
                Status.ERROR -> {
                    binding.progress.invisible()
                    Log.e("----------", "error: ${aboutClinic.message}")
                }
            }
        }
    }

    private fun setUpUI() {
        binding.tvAll.setOnClickListener {
            if (tvAll) {
                binding.tvAll.text = requireContext().getText(R.string.hide_all)
                val layoutManager = FlexboxLayoutManager(context)
                layoutManager.flexDirection = ROW
                layoutManager.justifyContent = JustifyContent.SPACE_BETWEEN
                binding.rvCategories.layoutManager = layoutManager
                tvAll = false
            } else {
                binding.tvAll.text = requireContext().getText(R.string.all)
                binding.rvCategories.layoutManager =
                    LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
                tvAll = true
            }
        }
        animationFab = AnimationUtils.loadAnimation(context, R.anim.anim_fab_open)
        binding.btnChat.setOnClickListener {
            ViewUtils.fadeOut(binding.btnChat)
            ViewUtils.fadeIn(binding.fab)
            binding.fab.startAnimation(animationFab)
            binding.fab.setOnClickListener {
                when (it) {
                    it -> findNavController().navigate(R.id.chatFragment)
                }
            }
        }
    }

    @LayoutRes
    override fun getLayoutResId(): Int = R.layout.fragment_home
    override val vm: HomeVM
        get() = ViewModelProvider(this)[HomeVM::class.java]

}