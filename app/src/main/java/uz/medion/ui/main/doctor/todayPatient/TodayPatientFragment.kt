package uz.medion.ui.main.doctor.todayPatient

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.data.model.doctor.MyPatientsItem
import uz.medion.databinding.FragmentTodayPatientBinding
import uz.medion.ui.main.doctor.myPatient.MyPatientAdapter

class TodayPatientFragment : Fragment(),MyPatientAdapter.PatientItemListener {

   private lateinit var binding:FragmentTodayPatientBinding
   private lateinit var adapter: MyPatientAdapter
    private lateinit var viewModel: TodayPatientViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_today_patient, container, false)
        setRv()
        return binding.root
    }

private fun setRv(){
    val list=Constants.getMyPatients()
    adapter= MyPatientAdapter(this)
    adapter.setData(list)
    binding.rvTodayPatient.layoutManager=
        LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)
    binding.rvTodayPatient.adapter=adapter
}

    override fun onClickPatient(patient: MyPatientsItem) {

    }
}