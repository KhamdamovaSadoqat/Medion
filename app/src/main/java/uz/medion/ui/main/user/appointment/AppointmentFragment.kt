package uz.medion.ui.main.user.appointment

import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uz.medion.R
import uz.medion.data.constants.Keys
import uz.medion.databinding.FragmentAppointmentBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.utils.gone
import uz.medion.utils.visible

class AppointmentFragment : BaseFragment<FragmentAppointmentBinding, AppointmentVM>() {

    private var type: String = ""
    private var doctorName: String = ""

    override fun onBound() {
        setUp()
        val args = arguments
        if (args != null) {
            if (args.containsKey(Keys.BUNDLE_APPOINTMENT_DOCTOR_NAME)) {
                doctorName = requireArguments().get(Keys.BUNDLE_APPOINTMENT_DOCTOR_NAME) as String
            }
        }
        binding.btnSubmit.setOnClickListener {
            findNavController().navigate(
                R.id.action_appointmentFragment_to_aboutDoctorFragment, bundleOf(
                    Pair(Keys.BUNDLE_APPOINTMENT_TYPE, type),
                    Pair(Keys.BUNDLE_APPOINTMENT_DOCTOR_NAME, doctorName)
                )
            )
        }
    }

    fun setUp() {
        type = binding.tvOption1.text.toString()
        binding.clOption1.setOnClickListener {
            binding.ivOption1.visible()
            binding.ivOption2.gone()
            binding.ivOption3.gone()
            type = binding.tvOption1.text.toString()
        }
        binding.clOption2.setOnClickListener {
            binding.ivOption1.gone()
            binding.ivOption2.visible()
            binding.ivOption3.gone()
            type = binding.tvOption2.text.toString()
        }
        binding.clOption3.setOnClickListener {
            binding.ivOption1.gone()
            binding.ivOption2.gone()
            binding.ivOption3.visible()
            type = binding.tvOption3.text.toString()
        }
    }

    override fun getLayoutResId() = R.layout.fragment_appointment
    override val vm: AppointmentVM
        get() = ViewModelProvider(this).get(AppointmentVM::class.java)

}