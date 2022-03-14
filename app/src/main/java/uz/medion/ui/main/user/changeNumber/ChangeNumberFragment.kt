package uz.medion.ui.main.user.changeNumber

import android.util.Log
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uz.medion.R
import uz.medion.data.constants.Keys
import uz.medion.data.model.RegistrationCreateRequest
import uz.medion.data.model.remote.Status
import uz.medion.databinding.FragmentChangeNumberBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.utils.gone
import uz.medion.utils.visible

class ChangeNumberFragment : BaseFragment<FragmentChangeNumberBinding, ChangeNumberVM>() {

    override fun onBound() {
        setUp()
    }

    fun setUp() {
        binding.btnSubmit.setOnClickListener {
            postResetPhone(RegistrationCreateRequest(binding.tiedNumber.text.toString()))
        }
    }

    override fun getLayoutResId() = R.layout.fragment_change_number
    override val vm: ChangeNumberVM
        get() = ViewModelProvider(this)[ChangeNumberVM::class.java]


    private fun postResetPhone(registrationCreateRequest: RegistrationCreateRequest) {
        vm.postResetPhone(registrationCreateRequest).observe(this) {
            when (it.status) {
                Status.LOADING -> {
                    binding.progress.visible()
                }
                Status.SUCCESS -> {
                    binding.progress.gone()
                    Log.d("----------", "postResetPhone: ${it.data.toString()}")
                    findNavController().navigate(
                        R.id.verificationFragment2,
                        bundleOf(Keys.BUNDLE_CHANGE_PHONE_NUMBER to "changing")
                    )
                }
                Status.ERROR -> {
                    binding.progress.gone()
                    Toast.makeText(requireContext(), it.message.toString(), Toast.LENGTH_SHORT)
                        .show()
                }
            }
        }
    }
}