package uz.medion.ui.main.user.home

import android.view.Gravity
import android.widget.GridLayout
import androidx.annotation.LayoutRes
import androidx.core.view.marginStart
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.databinding.FragmentHomeBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.ui.main.MainActivity
import uz.medion.utils.ViewUtils.setMargins


class HomeFragment : BaseFragment<FragmentHomeBinding, HomeVM>() {

    private lateinit var adapter: HomeAdapter
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
        adapter.setData(Constants.getHomeItems())
        binding.rvCategories.adapter = adapter
        binding.rvCategories.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)

        binding.tvAll.setOnClickListener {
            if (tvAll) {
                binding.rvCategories.layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                tvAll = false
            } else {
                binding.rvCategories.layoutManager = GridLayoutManager(requireContext(), 3)
                setMargins(binding.rvCategories, 50, 0, 0, 0)
                tvAll = true

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