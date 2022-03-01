package uz.medion.ui.main.user.esteticMedicine

import androidx.lifecycle.ViewModelProvider
import uz.medion.R
import uz.medion.data.model.remote.Status
import uz.medion.databinding.FragmentEsteticMedicineBinding
import uz.medion.ui.base.BaseFragment

class EsteticMedicineFragment : BaseFragment<FragmentEsteticMedicineBinding, EsteticMedicineVM>() {

    lateinit var adapter: EsteticMedicineViewPager

    override fun onBound() {
        setUp()
    }

    private fun setUp() {
//        val data = Constants.getEsteticMedicine_categories()
//        adapter = AboutDoctorAdapter { position, lastPosition ->
//            if (position != lastPosition) {
//                data[position] =
//                    AboutDoctorItems(
//                        data[position].categoryName,
//                        R.color.nile_blue_900,
//                        R.color.white
//                    )
//                data[lastPosition] = AboutDoctorItems(
//                    data[lastPosition].categoryName,
//                    R.color.solitude_50,
//                    R.color.tangaroa_900
//                )
//                adapter.setData(data)
//            }
//        }
//        adapter = EsteticMedicineViewPager(requireContext())
//        binding.viewPager.adapter = adapter

//        binding.rvEs3.adapter = adapter
//        binding.rvEs3.layoutManager =
//        LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)
//        adapter.setData(data)

        val adapter = EsteticMedicineViewPager(
            loadTitle(),
            childFragmentManager,
            lifecycle
        )
        binding.viewPager.adapter = adapter
        
    }

    private fun loadTitle() = listOf(
        "Topshirishingiz kutulmoqda",
        "3 kundan kam vaqt qoldi",
        "Yangii topshiriq",
        "Topshiriq topshirildi",
        "Topshiriq baholandi",
        "Topshiriq muddati tugadi",
        "Hammasi"
    )

    fun getEsteticMedicine(){
        vm.getEsteticMedicine().observe(this){
            when(it.status){
                Status.LOADING -> {}
                Status.SUCCESS -> {}
                Status.ERROR -> {}
            }
        }
    }

    override fun getLayoutResId() = R.layout.fragment_estetic_medicine

    override val vm: EsteticMedicineVM
        get() = ViewModelProvider(this)[EsteticMedicineVM::class.java]

}