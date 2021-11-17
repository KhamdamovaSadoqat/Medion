package uz.medion.ui.main.user.ourDoctors

import android.graphics.Point
import android.os.Build
import android.util.Log
import android.view.Display
import android.view.SurfaceView
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.flexbox.FlexDirection
import com.google.android.flexbox.FlexboxLayoutManager
import com.google.android.flexbox.JustifyContent
import com.prolificinteractive.materialcalendarview.CalendarDay
//import com.prolificinteractive.materialcalendarview.CalendarDay
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.data.constants.Keys
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
                binding.ivShowAll.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_show_all
                    )
                )
                binding.tvShowAll.setText(R.string.show_all)
                tvCategoryAll = false
            } else {
                val flexboxLayoutManager = FlexboxLayoutManager(context)
                flexboxLayoutManager.flexDirection = FlexDirection.ROW
                flexboxLayoutManager.justifyContent = JustifyContent.SPACE_BETWEEN
                binding.rvDoctorsCategories.removeAllViews()
                binding.rvDoctorsCategories.layoutManager = flexboxLayoutManager
                binding.clReason.visible()
                binding.clCalendar.visible()
                binding.ivShowAll.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_hide_all
                    )
                )
                binding.tvShowAll.setText(R.string.hide_all)
                tvCategoryAll = true
            }
        }

        ourDoctorsDetailsAdapter = OurDoctorsDetailsAdapter { item ->
            findNavController().navigate(
                R.id.aboutDoctorFragment,
                bundleOf(
                    Pair(
                        Keys.BUNDLE_APPOINTMENT_DOCTOR_NAME,
                        requireContext().getString(item.doctorName)
                    )
                )
            )
        }
        ourDoctorsDetailsAdapter.setData(Constants.getOurDoctorDetail())
        binding.rvDoctors.adapter = ourDoctorsDetailsAdapter
        binding.rvDoctors.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        val currentDate = CalendarDay.today()
        val calendarState = binding.cvCalendar.state().edit()
        calendarState.setMinimumDate(
            CalendarDay.from(
                currentDate.year,
                currentDate.month,
                currentDate.day
            )
        )
        if (currentDate.month == 12)
            calendarState.setMaximumDate(CalendarDay.from(currentDate.year + 1, 0, currentDate.day))
        calendarState.setMaximumDate(
            CalendarDay.from(
                currentDate.year,
                currentDate.month + 1,
                currentDate.day
            )
        )
        calendarState.commit()


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