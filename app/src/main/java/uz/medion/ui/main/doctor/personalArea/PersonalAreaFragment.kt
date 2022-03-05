package uz.medion.ui.main.doctor.personalArea

import android.content.Context
import android.graphics.BitmapFactory
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uz.medion.R
import uz.medion.data.model.remote.Status
import uz.medion.databinding.FragmentPersonalAreaBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.ui.main.doctor.profileDoctor.ChangeProfileDoctorFragmentViewModel
import uz.medion.utils.ImageDownloader
import java.net.URL

class PersonalAreaFragment : BaseFragment<FragmentPersonalAreaBinding,ChangeProfileDoctorFragmentViewModel>() {


    override fun getLayoutResId() = R.layout.fragment_personal_area
    override fun onBound() {
        vm.getDoctorId().observe(viewLifecycleOwner) { doctor ->
            when(doctor.status){
                Status.LOADING->{}
                Status.SUCCESS->{
                    binding.fullName.setText(doctor.data?.fullName)
                    ImageDownloader.loadImage(requireContext(),doctor.data!!.image,binding.profileImg)

                }
                Status.ERROR->{}
            }
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

    }

    override val vm: ChangeProfileDoctorFragmentViewModel
        get() = ViewModelProvider(this).get(ChangeProfileDoctorFragmentViewModel::class.java)

private fun profileImage(url:String ,context: Context){

    ImageDownloader.loadImage(context,url,binding.profileImg)
}
}