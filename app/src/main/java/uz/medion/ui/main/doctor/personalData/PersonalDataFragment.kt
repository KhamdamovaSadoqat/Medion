package uz.medion.ui.main.doctor.personalData

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.medion.R

class PersonalDataFragment : Fragment() {

    companion object {
        fun newInstance() = PersonalDataFragment()
    }

    private lateinit var viewModel: PersonalDataViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_change_profile_doctor, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PersonalDataViewModel::class.java)
        // TODO: Use the ViewModel
    }

}