package uz.medion.ui.main.user.search


import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.data.constants.Keys
import uz.medion.data.model.DoctorResponse
import uz.medion.data.model.ItemSearch
import uz.medion.data.model.remote.Resource
import uz.medion.data.model.remote.Status
import uz.medion.databinding.FragmentSearchViewBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.ui.main.user.ourDoctors.OurDoctorsFragmentDirections
import uz.medion.utils.gone
import uz.medion.utils.invisible
import uz.medion.utils.visible

class SearchViewFragment : BaseFragment<FragmentSearchViewBinding, SearchViewVM>() {

    private lateinit var doctorAdapter: DoctorAdapter
    private lateinit var serviceAdapter: ServiceAdapter
    private lateinit var cabinetAdapter: CabinetAdapter
    private lateinit var mainAdapter: MainAdapter

    override fun onBound() {
        setUp()
    }

    private fun setUp() {
        with(vm) {
            joinMainsLiveData.observe(requireActivity(), joinMainsObserver)
            joinProfileSettingsLiveData.observe(requireActivity(), joinProfileSettingsObserver)
            joinDoctorsLiveData.observe(requireActivity(), joinDoctorsObserver)
            joinSpecialityLiveData.observe(requireActivity(), joinSpecialityObserver)
        }

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null && query != "") {
                    vm.onSearch(query.trim())
                    Log.d("1111", "onQueryTextChange:$query")
                } else vm.onEmptySearch()
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (newText == null || newText == "") {
                    vm.onEmptySearch()
                }
                return true
            }
        })

        doctorAdapter = DoctorAdapter {
            val action =
                SearchViewFragmentDirections.actionSearchViewFragmentToAboutDoctorFragment(
                    it)
            findNavController().navigate(action)
        }

        serviceAdapter = ServiceAdapter {
            val action =
                SearchViewFragmentDirections.actionSearchViewFragmentToOurDoctorsFragment(
                    it)
            findNavController().navigate(action)
        }

        cabinetAdapter = CabinetAdapter {
            when (it) {
                0 -> {
                    findNavController().navigate(R.id.changeNumberFragment)
                }
                1 -> {
                    findNavController().navigate(R.id.changePasswordFragment)
                }
                2 -> {
                    findNavController().navigate(R.id.personalDateFragment)
                }
                3 -> {
                    findNavController().navigate(R.id.myDocumentsFragment)
                }
                4 -> {
                    findNavController().navigate(R.id.myDoctorsFragment)
                }
                5 -> {
                    findNavController().navigate(R.id.chooseLanguageFragment)
                }
            }
        }

        mainAdapter = MainAdapter {
            when (it) {
                0 -> {
                    findNavController().navigate(R.id.ourDoctorsFragment)
                }
                1 -> {
                    findNavController().navigate(R.id.esteticMedicineFragment)
                }
                2 -> {
                    findNavController().navigate(R.id.spaMedicineFragment)
                }
                3 -> {
                    findNavController().navigate(R.id.adressAndContactsFragment)
                }
                4 -> {
                    findNavController().navigate(R.id.personalAccountFragment)
                }
            }
        }

        binding.rvDoctorsSearch.layoutManager =
            LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
        binding.rvDoctorsSearch.adapter = doctorAdapter

        binding.rvServicesSearch.layoutManager =
            LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
        binding.rvServicesSearch.adapter = serviceAdapter


        binding.rvCabinSearch.layoutManager =
            LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
        binding.rvCabinSearch.adapter = cabinetAdapter

        binding.rvMainSearch.layoutManager =
            LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
        binding.rvMainSearch.adapter = mainAdapter

    }


    override fun getLayoutResId() = R.layout.fragment_search_view

    override val vm: SearchViewVM
        get() = ViewModelProvider(this).get(SearchViewVM::class.java)

    private val joinMainsObserver = Observer<Resource<List<ItemSearch>>> {
        when (it.status) {
            Status.LOADING -> {
                binding.tvMain.gone()
            }
            Status.SUCCESS -> {
                binding.tvMain.visible()
                mainAdapter.setData(it.data as ArrayList<ItemSearch> /* = java.util.ArrayList<uz.medion.data.model.ItemSearch> */)
            }
            Status.ERROR -> {
                binding.tvMain.gone()
            }
        }
    }

    private val joinProfileSettingsObserver = Observer<Resource<List<ItemSearch>>> {
        when (it.status) {
            Status.LOADING -> {
                binding.tvCabin.gone()
            }
            Status.SUCCESS -> {
                binding.tvCabin.visible()
                cabinetAdapter.setData(it.data as ArrayList<ItemSearch> /* = java.util.ArrayList<uz.medion.data.model.ItemSearch> */)
            }
            Status.ERROR -> {
                binding.tvCabin.gone()
            }
        }
    }

    private val joinDoctorsObserver = Observer<Resource<List<DoctorResponse>>> {
        when (it.status) {
            Status.LOADING -> {
                binding.tvDoctors.gone()
            }
            Status.SUCCESS -> {
                binding.tvDoctors.visible()
                doctorAdapter.setData(it.data as ArrayList<DoctorResponse> /* = java.util.ArrayList<uz.medion.data.model.ItemSearch> */)
            }
            Status.ERROR -> {
                binding.tvDoctors.gone()
            }
        }
    }

    private val joinSpecialityObserver = Observer<Resource<List<ItemSearch>>> {
        when (it.status) {
            Status.LOADING -> {
                binding.tvServices.gone()
            }
            Status.SUCCESS -> {
                binding.tvServices.visible()
                serviceAdapter.setData(it.data as ArrayList<ItemSearch> /* = java.util.ArrayList<uz.medion.data.model.ItemSearch> */)
            }
            Status.ERROR -> {
                binding.tvServices.gone()
            }
        }
    }

}

//
//    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
//       // inflater.inflate(R.menu.search_view_menu, menu)
//
//      //  val menuitem = menu.findItem(R.id.iv_search_view)
//
//
//        if (menuitem != null) {
//            val searchView = menuitem.actionView as SearchView
//
//            searchView.maxWidth = Int.MAX_VALUE
//
//
//            searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
//                override fun onQueryTextSubmit(query: String?): Boolean {
//                    mainAdapter.filter.filter(query)
//
//                    return true
//                }
//
//                override fun onQueryTextChange(newText: String?): Boolean {
//                    mainAdapter.filter.filter(newText)
//
//                    return true
//                }
//            })
//        }
//
//    }
//}