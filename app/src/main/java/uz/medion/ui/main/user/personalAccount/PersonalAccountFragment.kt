package uz.medion.ui.main.user.personalAccount

import android.content.Intent
import android.provider.SyncStateContract
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.data.model.remote.Status
import uz.medion.databinding.FragmentPersonalAccountBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.ui.main.MainActivity
import uz.medion.ui.splash.SplashActivity
import uz.medion.utils.ImageDownloader
import uz.medion.utils.visible

class PersonalAccountFragment : BaseFragment<FragmentPersonalAccountBinding, PersonalAccountVM>() {

    override fun onBound() {
        setUp()
        getProfile()
    }

    fun setUp() {
        binding.cl1.setOnClickListener {
            findNavController().navigate(R.id.action_personalAccountFragment_to_changeNumberFragment)
        }
        binding.cl2.setOnClickListener {
            findNavController().navigate(R.id.action_personalAccountFragment_to_changePasswordFragment)
        }
        binding.cl3.setOnClickListener {
            findNavController().navigate(R.id.action_personalAccountFragment_to_personalDateFragment)
        }
        binding.cl4.setOnClickListener {
            findNavController().navigate(R.id.action_personalAccountFragment_to_myDocumentsFragment)
        }
        binding.cl5.setOnClickListener {
            findNavController().navigate(R.id.action_personalAccountFragment_to_myDoctorsFragment)
        }
        binding.cl6.setOnClickListener {
            findNavController().navigate(R.id.action_personalAccountFragment_to_chooseLanguageFragment)
        }
        binding.cl7.setOnClickListener {
            prefs.isRegistered = false
            Constants.setUnAuthorized(true)
            Constants.token = ""
        }
    }

    override fun getLayoutResId() = R.layout.fragment_personal_account
    override val vm: PersonalAccountVM
        get() = ViewModelProvider(this)[PersonalAccountVM::class.java]

    private fun getProfile() {
        vm.getProfile().observe(this) { profile ->
            when (profile.status) {
                Status.LOADING -> {}
                Status.SUCCESS -> {
                    binding.clUserInfo.visible()
                    ImageDownloader.loadImage(requireContext(),
                        profile.data?.image.toString(),
                        binding.shivUserPic)
                    binding.tvUserName.text = profile.data?.fullName ?: ""
                    binding.tvMobilePhone.text = profile.data?.username as CharSequence?
                }
                Status.ERROR -> {}
            }
        }
    }




}