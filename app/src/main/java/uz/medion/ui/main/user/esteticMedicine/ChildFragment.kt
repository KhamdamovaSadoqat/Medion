package uz.medion.ui.main.user.esteticMedicine

import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import uz.medion.R
import uz.medion.data.constants.Keys
import uz.medion.data.model.remote.Status
import uz.medion.databinding.FragmentChildBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.utils.ImageDownloader

class ChildFragment : BaseFragment<FragmentChildBinding, EsteticMedicineVM>() {

    var position = 0

    override fun onBound() {
        setUpViews()
        getEsteticMedicine()
    }

    private fun setUpViews() {
        val bundle = Bundle()
        position = bundle.getInt(Keys.BUNDLE_ESTETIC_MEDICINE_POSITION)
    }

    private fun getEsteticMedicine() {
        vm.getEsteticMedicine().observe(this) { data ->
            when (data.status) {
                Status.LOADING -> {}
                Status.SUCCESS -> {
                    ImageDownloader.loadImage(requireContext(),
                        data.data!![0].childResponses[position].imageResponses[0].url,
                        binding.ivMain)
                    binding.tvMain.text = data.data[0].childResponses[position].text
                    ImageDownloader.loadImage(requireContext(),
                        data.data[0].childResponses[position].imageResponses[1].url,
                        binding.ivChild1)
                    ImageDownloader.loadImage(requireContext(),
                        data.data[0].childResponses[position].imageResponses[2].url,
                        binding.ivChild2)
                    ImageDownloader.loadImage(requireContext(),
                        data.data[0].childResponses[position].imageResponses[3].url,
                        binding.ivChild3)
                }
                Status.ERROR -> {}
            }
        }
    }

    override fun getLayoutResId() = R.layout.fragment_child

    override val vm: EsteticMedicineVM
        get() = ViewModelProvider(this)[EsteticMedicineVM::class.java]

}