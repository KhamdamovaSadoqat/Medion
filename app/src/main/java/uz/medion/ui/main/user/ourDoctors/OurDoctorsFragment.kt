package uz.medion.ui.main.user.ourDoctors

import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.databinding.FragmentOurDoctorsBinding
import uz.medion.ui.base.BaseFragment

class OurDoctorsFragment : BaseFragment<FragmentOurDoctorsBinding, OurDoctorsVM>() {

    private lateinit var ourDoctorsCategoryAdapter: OurDoctorsCategoryAdapter
    private lateinit var ourDoctorsDetailsAdapter: OurDoctorsDetailsAdapter
    private var tvCategoryAll: Boolean = false

    override fun onBound() {
        setUp()
    }

    fun setUp() {
        ourDoctorsCategoryAdapter = OurDoctorsCategoryAdapter {}
        ourDoctorsCategoryAdapter.setData(Constants.getOurDoctorCategory())
        binding.rvDoctorsCategories.adapter = ourDoctorsCategoryAdapter
        binding.rvDoctorsCategories.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        binding.clShowAll.setOnClickListener {
            if (tvCategoryAll) {
                binding.rvDoctorsCategories.layoutManager =
                    LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
                binding.clReason.visibility = View.GONE
                binding.clCalendar.visibility = View.GONE
                tvCategoryAll = false
            } else {
                binding.rvDoctorsCategories.layoutManager = GridLayoutManager(requireContext(), 3)
                binding.clReason.visibility = View.VISIBLE
                binding.clCalendar.visibility = View.VISIBLE
                tvCategoryAll = true
            }
        }

        ourDoctorsDetailsAdapter = OurDoctorsDetailsAdapter {
            findNavController().navigate(R.id.aboutDoctorFragment)
        }
        ourDoctorsDetailsAdapter.setData(Constants.getOurDoctorDetail())
        binding.rvDoctors.adapter = ourDoctorsDetailsAdapter
        binding.rvDoctors.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

    }

    override fun getLayoutResId() = R.layout.fragment_our_doctors
    override val vm: OurDoctorsVM
        get() = ViewModelProvider(this).get(OurDoctorsVM::class.java)

}