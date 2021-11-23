package uz.medion.ui.doctor.personalArea

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import uz.medion.R
import uz.medion.databinding.FragmentPersonalAreaBinding

class PersonalAreaFragment : Fragment() {
    private lateinit var binding:FragmentPersonalAreaBinding




    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
       binding=DataBindingUtil.inflate(inflater,R.layout.fragment_personal_area, container, false)
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


        return binding.root
    }




}