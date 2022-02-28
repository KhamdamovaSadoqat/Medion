package uz.medion.ui.main.doctor.personalArea

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uz.medion.R
import uz.medion.data.model.doctor.DoctorGetResponse
import uz.medion.databinding.FragmentPersonalAreaBinding

class PersonalAreaFragment : Fragment() {
    private lateinit var binding:FragmentPersonalAreaBinding
    private lateinit var viewModel:PersonalAreaVM




    @SuppressLint("LogConditional")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
       binding=DataBindingUtil.inflate(inflater,R.layout.fragment_personal_area, container, false)
        viewModel= ViewModelProvider(this)[PersonalAreaVM::class.java]
        viewModel.getDoctorInfo().observe(viewLifecycleOwner){doctor->

            binding.doctorInfo=doctor.data
            Log.d("PersonalArea", "onCreateView: ${doctor.data}")
        }
        binding.personalData.setOnClickListener {
            findNavController().navigate(R.id.changeProfileDoctorFragment)
        }
        binding.myPatient.setOnClickListener {
            findNavController().navigate(R.id.myPtientFragment)
        }
        binding.todayPatients.setOnClickListener {
            findNavController().navigate(R.id.todayPatientFragment)
        }
        binding.language.setOnClickListener {
            findNavController().navigate(R.id.chooseLanguageFragment2)
        }
        binding.logOut.setOnClickListener {
            findNavController().navigate(R.id.patientInfoFragment)
        }

        return binding.root
    }




}