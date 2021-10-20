package uz.medion.ui.main.user.esteticMedicine

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.data.model.AboutDoctorItems
import uz.medion.databinding.FragmentEsteticMedicineBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.ui.main.MainVM
import uz.medion.ui.main.user.aboutDoctor.AboutDoctorAdapter

class EsteticMedicineFragment : BaseFragment<FragmentEsteticMedicineBinding, MainVM>() {

    lateinit var adapter: AboutDoctorAdapter

    override fun onBound() {
        setUp()
    }

    private fun setUp() {
        val data = Constants.getEsteticMedicine_categories()
        adapter = AboutDoctorAdapter { position, lastPosition ->
            if (position != lastPosition) {
                data[position] =
                    AboutDoctorItems(
                        data[position].categoryName,
                        R.color.nile_blue_900,
                        R.color.white
                    )
                data[lastPosition] = AboutDoctorItems(
                    data[lastPosition].categoryName,
                    R.color.solitude_50,
                    R.color.tangaroa_900
                )
                adapter.setData(data)
            }
        }
        binding.rvEs3.adapter = adapter
        binding.rvEs3.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
        adapter.setData(data)
    }


    override fun getLayoutResId() = R.layout.fragment_estetic_medicine

    override val vm: MainVM
        get() = ViewModelProvider(this).get(MainVM::class.java)

}