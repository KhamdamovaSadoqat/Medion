package uz.medion.ui.main.user.appointment

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import uz.medion.R
import uz.medion.databinding.FragmentAppointmentBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.utils.gone
import uz.medion.utils.visible

class AppointmentFragment : BaseFragment<FragmentAppointmentBinding, AppointmentVM>() {

    private val args: AppointmentFragmentArgs by navArgs()
    private var type: String = ""
    private var doctorName: String = ""

    override fun onBound() {
        doctorName = args.appointmentDoctorName

        binding.btnSubmit.setOnClickListener {
            val action =
                AppointmentFragmentDirections.actionAppointmentFragmentToAboutDoctorFragment(type,
                    doctorName)
            findNavController().navigate(action)
        }
    }

    override fun getLayoutResId() = R.layout.fragment_appointment
    override val vm: AppointmentVM
        get() = ViewModelProvider(this)[AppointmentVM::class.java]

}