package uz.medion.ui.main.user.ourDoctors

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.databinding.FragmentOurDoctorsBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.utils.gone
import uz.medion.utils.visible


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
                binding.clReason.gone()
                binding.clCalendar.gone()
                tvCategoryAll = false
            } else {
                val flexboxLayoutManager = FlexboxLayoutManager(context)
                flexboxLayoutManager.flexDirection = FlexDirection.COLUMN
                flexboxLayoutManager.justifyContent = JustifyContent.FLEX_END
                binding.rvDoctorsCategories.layoutManager = flexboxLayoutManager
                binding.clReason.visible()
                binding.clCalendar.visible()
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

        binding.cvCalendar.date = System.currentTimeMillis()

        binding.clOption1.setOnClickListener {
            binding.ivOption1.visible()
            binding.ivOption2.gone()
            binding.ivOption3.gone()
        }
        binding.clOption2.setOnClickListener {
            binding.ivOption1.gone()
            binding.ivOption2.visible()
            binding.ivOption3.gone()
        }
        binding.clOption3.setOnClickListener {
            binding.ivOption1.gone()
            binding.ivOption2.gone()
            binding.ivOption3.visible()
        }

    }

    override fun getLayoutResId() = R.layout.fragment_our_doctors
    override val vm: OurDoctorsVM
        get() = ViewModelProvider(this).get(OurDoctorsVM::class.java)

}