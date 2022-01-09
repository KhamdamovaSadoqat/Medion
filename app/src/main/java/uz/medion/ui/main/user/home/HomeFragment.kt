package uz.medion.ui.main.user.home

import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.*
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.databinding.FragmentHomeBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.utils.ViewUtils
import uz.medion.utils.ViewUtils.setMargins


class HomeFragment : BaseFragment<FragmentHomeBinding, HomeVM>() {

    private lateinit var adapter: HomeAdapter
    private lateinit var adapter2: HomeAdapter
    private lateinit var animationFab:Animation
    private var tvAll: Boolean = false

    override fun onBound() {
        setUp()
    }

    fun setUp() {
        adapter = HomeAdapter {
            when (it) {
                it -> findNavController().navigate(R.id.ourDoctorsFragment)
            }
        }
         adapter2 = HomeAdapter {
            when (it) {
                it -> findNavController().navigate(R.id.ourDoctorsFragment)
            }
        }

        adapter.setData(Constants.getHomeItems())
        adapter2.setData(Constants.getHomeItems())
        binding.list.enableViewScaling(true)
        binding.list.adapter = adapter
        binding.rvCategories2.adapter = adapter2

        binding.tvAll.setOnClickListener {

          if (tvAll)
            {
                ViewUtils.fadeIn(binding.rvCategories2)
                ViewUtils.fadeOut(binding.list)
                ViewUtils.fadeOut(binding.cvItem)
                ViewUtils.fadeOut(binding.tvAboutOurCenter)
                ViewUtils.fadeOut(binding.tvAboutOurCenterInfo)

                binding.rvCategories2.layoutManager = GridLayoutManager(requireContext(), 3)
                setMargins(binding.list, 50, 0, 0, 0)
                tvAll = false

            } else
            {
                ViewUtils.fadeIn(binding.list)
                ViewUtils.fadeOut(binding.rvCategories2)
                ViewUtils.fadeIn(binding.cvItem)
                ViewUtils.fadeIn(binding.tvAboutOurCenter)
                ViewUtils.fadeIn(binding.tvAboutOurCenterInfo)
                tvAll = true

            }
        }
        animationFab=AnimationUtils.loadAnimation(context,R.anim.anim_fab_open)
        binding.btnChat.setOnClickListener {
            ViewUtils.fadeOut(binding.btnChat)
            ViewUtils.fadeIn(binding.fab)
            binding.fab.startAnimation(animationFab)

            binding.fab.setOnClickListener {
                when(it){
                    it->findNavController().navigate(R.id.chatFragment)
                }
            }

        }
    }

//    fun setUpUI(){
//        val activity = requireActivity() as MainActivity
//    }

    @LayoutRes
    override fun getLayoutResId(): Int = R.layout.fragment_home
    override val vm: HomeVM
        get() = ViewModelProvider(this).get(HomeVM::class.java)

}