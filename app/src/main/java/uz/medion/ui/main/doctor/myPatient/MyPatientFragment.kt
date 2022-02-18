package uz.medion.ui.main.doctor.myPatient

import android.os.Build
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.data.model.doctor.DoctorMyPacientesResponseItem
import uz.medion.data.model.doctor.MyPatientsItem
import uz.medion.databinding.FragmentMyPtientBinding

class MyPatientFragment : Fragment(), MyPatientAdapter.PatientItemListener {
    private lateinit var binding:FragmentMyPtientBinding
    private lateinit var adapter: MyPatientAdapter

    companion object {
        fun newInstance() = MyPatientFragment()
    }

    private lateinit var viewModel: MyPatientViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding=DataBindingUtil.inflate(inflater,R.layout.fragment_my_ptient, container, false)

        binding.tvFavouritePatient.setOnClickListener {
            binding.tvFavouritePatient.background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_blue_8)
            binding.tvAllPatients.background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_solitude_8)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                binding.tvAllPatients.setTextColor(requireContext().getColor(R.color.tangaroa_900))
                binding.tvFavouritePatient.setTextColor(requireContext().getColor(R.color.white))
            } else{
                binding.tvFavouritePatient.setTextColor(resources.getColor(R.color.white))
                binding.tvAllPatients.setTextColor(resources.getColor(R.color.tangaroa_900))
            }


        }
        binding.tvAllPatients.setOnClickListener {
            binding.tvFavouritePatient.background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_solitude_8)
            binding.tvAllPatients.background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_blue_8)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                binding.tvAllPatients.setTextColor(requireContext().getColor(R.color.white))
                binding.tvFavouritePatient.setTextColor(requireContext().getColor(R.color.tangaroa_900))
            } else{
                binding.tvFavouritePatient.setTextColor(resources.getColor(R.color.tangaroa_900))
                binding.tvAllPatients.setTextColor(resources.getColor(R.color.white))
            }

//            myDoctorsAdapter.setData(Constants.getMyDoctors())

        }
        return binding.root
    }

    private fun setRv(patient:List<DoctorMyPacientesResponseItem>) {
        adapter= MyPatientAdapter(this)
        adapter.setData(patient)
        binding.rvMyPatient.layoutManager=
            LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
        binding.rvMyPatient.adapter=adapter
    }
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MyPatientViewModel::class.java)

        // TODO: Use the ViewModel
    }

    override fun onClickPatient(patient: DoctorMyPacientesResponseItem) {
if (patient.isFavorite){
    binding.tvFavouritePatient.setOnClickListener {
        binding.tvFavouritePatient.background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_blue_8)
        binding.tvAllPatients.background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_solitude_8)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            binding.tvAllPatients.setTextColor(requireContext().getColor(R.color.tangaroa_900))
            binding.tvFavouritePatient.setTextColor(requireContext().getColor(R.color.white))
        } else{
            binding.tvFavouritePatient.setTextColor(resources.getColor(R.color.white))
            binding.tvAllPatients.setTextColor(resources.getColor(R.color.tangaroa_900))
        }


    }

}
    }

}