package uz.medion.ui.main.user.esteticMedicine

import android.util.Log
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import uz.medion.R
import uz.medion.data.model.remote.Status
import uz.medion.databinding.FragmentEsteticMedicineBinding
import uz.medion.databinding.ItemDoctorCategoryBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.utils.invisible
import uz.medion.utils.visible

class EsteticMedicineFragment : BaseFragment<FragmentEsteticMedicineBinding, EsteticMedicineVM>() {

    lateinit var adapter: EsteticMedicineViewPager

    override fun onBound() {
        getEsteticMedicine()
    }

    private fun getEsteticMedicine() {
        vm.getEsteticMedicine().observe(this) { data ->
            when (data.status) {
                Status.LOADING -> {
                    binding.progress.visible()
                    Log.d("----------", "getEsteticMedicine: loading")
                }
                Status.SUCCESS -> {
                    binding.progress.invisible()
                    val list = arrayListOf<String>()
                    for (title in data.data!![0].childResponses) {
                        list.add(title.mainTitle)
                    }
                    Log.d("----------", "getEsteticMedicine: list: $list")
                    val adapter = EsteticMedicineViewPager(
                        list,
                        childFragmentManager,
                        lifecycle
                    )
                    binding.viewPager.adapter = adapter


                    TabLayoutMediator(binding.tabLayout, binding.viewPager, true) { tab, position ->
                        val tabView = ItemDoctorCategoryBinding.inflate(layoutInflater,
                            binding.tabLayout,
                            false)
                        tabView.tvCategory.text = list[position]
                        if (position == 0) {
                            tabView.cvCard.background = ContextCompat.getDrawable(requireContext(),
                                R.drawable.bg_blue_16)
                            tabView.tvCategory.setTextColor(ContextCompat.getColor(requireContext(),
                                R.color.white))
                        } else {
                            tabView.cvCard.background = ContextCompat.getDrawable(requireContext(),
                                R.drawable.bg_transparent_16)
                            tabView.tvCategory.setTextColor(ContextCompat.getColor(requireContext(),
                                R.color.tangaroa_900))
                        }
                        tab.customView = tabView.root
                    }.attach()

                    binding.tabLayout.addOnTabSelectedListener(object :
                        TabLayout.OnTabSelectedListener {
                        override fun onTabSelected(tab: TabLayout.Tab?) {
                            tab?.customView?.background =
                                ContextCompat.getDrawable(requireContext(),
                                    R.drawable.bg_blue_16)
                            tab?.customView?.findViewById<TextView>(R.id.tv_category)
                                ?.setTextColor(ContextCompat.getColor(requireContext(),
                                    R.color.white))
                        }

                        override fun onTabUnselected(tab: TabLayout.Tab?) {
                            tab?.customView?.background =
                                ContextCompat.getDrawable(requireContext(),
                                    R.drawable.bg_transparent_16)
                            tab?.customView?.findViewById<TextView>(R.id.tv_category)
                                ?.setTextColor(ContextCompat.getColor(requireContext(),
                                    R.color.tangaroa_900))
                        }

                        override fun onTabReselected(tab: TabLayout.Tab?) {
                        }
                    })
                }
                Status.ERROR -> {
                    binding.progress.invisible()
                    Log.d("----------", "getEsteticMedicine: error: ${data.status}")
                    Log.d("----------", "getEsteticMedicine: error: ${data.message}")
                    Log.d("----------", "getEsteticMedicine: error: ${data.throwable}")
                }
            }
        }
    }

    override fun getLayoutResId() = R.layout.fragment_estetic_medicine

    override val vm: EsteticMedicineVM
        get() = ViewModelProvider(this)[EsteticMedicineVM::class.java]

}