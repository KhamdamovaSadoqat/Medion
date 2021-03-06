package uz.medion.ui.main.user.search


import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.data.constants.Keys
import uz.medion.databinding.FragmentSearchViewBinding
import uz.medion.ui.base.BaseFragment

class SearchViewFragment : BaseFragment<FragmentSearchViewBinding, SearchViewVM>() {

    private lateinit var doctorAdapter: DoctorAdapter
    private lateinit var serviceAdapter: ServiceAdapter
    private lateinit var cabinetAdapter: CabinetAdapter
    private lateinit var mainAdapter: MainAdapter
    private lateinit var shortDescriptionAdapter: ShortDescriptionAdapter

    override fun onBound() {
        setUp()
    }

    private fun setUp() {

        doctorAdapter = DoctorAdapter {
            findNavController().navigate(
                R.id.aboutDoctorFragment)

        }
        doctorAdapter.setData(Constants.getMyDoctorsItem())
        serviceAdapter = ServiceAdapter { }
        serviceAdapter.setData(Constants.getOurDoctorCategory())

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
        cabinetAdapter.setData(Constants.getMyAccount())

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
        mainAdapter.setData(
            Constants.getMyMain()
        )

        shortDescriptionAdapter = ShortDescriptionAdapter {
            when (it) {
                0 -> {
                    findNavController().navigate(R.id.spaMedicineDetailsFragment)
                }
                1 -> {
                    findNavController().navigate(R.id.spaMedicineDetailsFragment)
                }
                2 -> {
                    findNavController().navigate(R.id.spaMedicineDetailsFragment)
                }
                3 -> {
                    findNavController().navigate(R.id.spaMedicineDetailsFragment)
                }
                4 -> {
                    findNavController().navigate(R.id.spaMedicineDetailsFragment)
                }
                5 -> {
                    findNavController().navigate(R.id.spaMedicineDetailsFragment)
                }
                6 -> {
                    findNavController().navigate(R.id.spaMedicineDetailsFragment)
                }
                7 -> {
                    findNavController().navigate(R.id.spaMedicineDetailsFragment)
                }
                8 -> {
                    findNavController().navigate(R.id.spaMedicineDetailsFragment)
                }
                9 -> {
                    findNavController().navigate(R.id.spaMedicineDetailsFragment)
                }
                10 -> {
                    findNavController().navigate(R.id.spaMedicineDetailsFragment)
                }

            }

        }
        shortDescriptionAdapter.setData(Constants.getSpaHeaders())

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


        binding.rvDescripionSearch.layoutManager =
            LinearLayoutManager(requireActivity(), RecyclerView.VERTICAL, false)
        binding.rvDescripionSearch.adapter = shortDescriptionAdapter

    }

    override fun getLayoutResId() = R.layout.fragment_search_view

    override val vm: SearchViewVM
        get() = ViewModelProvider(this).get(SearchViewVM::class.java)

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
}