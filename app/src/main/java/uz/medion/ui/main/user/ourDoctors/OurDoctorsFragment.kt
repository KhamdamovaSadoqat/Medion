package uz.medion.ui.main.user.ourDoctors

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
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
                tvCategoryAll = false
            } else {
                binding.rvDoctorsCategories.layoutManager = GridLayoutManager(requireContext(), 3)
                tvCategoryAll = true
            }
        }

        ourDoctorsDetailsAdapter = OurDoctorsDetailsAdapter { }
        ourDoctorsDetailsAdapter.setData(Constants.getOurDoctorDetail())
        binding.rvDoctors.adapter = ourDoctorsDetailsAdapter
        binding.rvDoctors.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

    }

    override fun getLayoutResId() = R.layout.fragment_our_doctors
    override val vm: OurDoctorsVM
        get() = ViewModelProvider(this).get(OurDoctorsVM::class.java)

}