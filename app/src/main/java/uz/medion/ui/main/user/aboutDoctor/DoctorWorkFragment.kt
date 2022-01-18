package uz.medion.ui.main.user.aboutDoctor

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.databinding.FragmentDoctorWorkBinding
import uz.medion.ui.base.BaseFragment

class DoctorWorkFragment : BaseFragment<FragmentDoctorWorkBinding, AboutDoctorVM>() {

    private lateinit var aboutDoctorWorkCurrentAdapter: AboutDoctorWorkAdapter
    private lateinit var aboutDoctorWorkPastAdapter: AboutDoctorWorkAdapter

    override fun onBound() {
        loadWork()
    }


    private fun loadWork() {
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

    override fun getLayoutResId() = R.layout.fragment_doctor_work
    override val vm: AboutDoctorVM
        get() = ViewModelProvider(this).get(AboutDoctorVM::class.java)

}