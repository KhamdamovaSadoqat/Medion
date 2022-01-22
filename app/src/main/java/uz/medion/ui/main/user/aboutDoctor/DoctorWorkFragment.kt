package uz.medion.ui.main.user.aboutDoctor

import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.data.constants.Keys
import uz.medion.data.model.WorkInfoListItem
import uz.medion.databinding.FragmentDoctorWorkBinding
import uz.medion.ui.base.BaseFragment

class DoctorWorkFragment : BaseFragment<FragmentDoctorWorkBinding, AboutDoctorVM>() {

    private lateinit var aboutDoctorWorkPastAdapter: AboutDoctorWorkAdapter

    override fun onBound() {

        //workPast
        aboutDoctorWorkPastAdapter = AboutDoctorWorkAdapter()
        binding.rvWorkPast.adapter = aboutDoctorWorkPastAdapter
        binding.rvWorkPast.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        val args = arguments
        if (args != null) {
            args.getParcelableArrayList<WorkInfoListItem>(Keys.BUNDLE_ABOUT_DOCTOR_WORK)?.let {
                aboutDoctorWorkPastAdapter.setData(it)
                //current
                binding.tvClinicName.text = it[0].organization
                binding.tvSphere.text  = it[0].position
            }
        }
    }

    override fun getLayoutResId() = R.layout.fragment_doctor_work
    override val vm: AboutDoctorVM
        get() = ViewModelProvider(this).get(AboutDoctorVM::class.java)

}