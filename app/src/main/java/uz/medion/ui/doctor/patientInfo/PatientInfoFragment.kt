package uz.medion.ui.doctor.patientInfo

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.medion.R

class PatientInfoFragment : Fragment() {

    companion object {
        fun newInstance() = PatientInfoFragment()
    }

    private lateinit var viewModel: PatientInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_patient_info, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PatientInfoViewModel::class.java)
        // TODO: Use the ViewModel
    }

}