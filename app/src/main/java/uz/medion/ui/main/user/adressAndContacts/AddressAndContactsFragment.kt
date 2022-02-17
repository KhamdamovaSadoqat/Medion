package uz.medion.ui.main.user.adressAndContacts

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.model.remote.Status
import uz.medion.databinding.FragmentAdressAndContactsBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.utils.invisible
import uz.medion.utils.visible

class AddressAndContactsFragment :
    BaseFragment<FragmentAdressAndContactsBinding, AddressAndContactsVM>() {

    lateinit var addressAndContactsAdapter: AddressAndContactsAdapter


    override fun onBound() {
        setUp()

    }

    private fun setUp() {
        vm.branch().observe(this) { branch ->
            when (branch.status) {
                Status.LOADING -> {
                    binding.progress.visible()
                }
                Status.SUCCESS -> {
                    binding.progress.invisible()
                    binding.rvAdressAndContacts.visible()
                    addressAndContactsAdapter.setData(branch.data!!)
                }
                Status.ERROR -> {
                }
            }
        }

        addressAndContactsAdapter = AddressAndContactsAdapter { branch ->
            val action =
                AddressAndContactsFragmentDirections.actionAdressAndContactsFragmentToAddressFragment(
                    branch)
            findNavController().navigate(action)
        }
        binding.rvAdressAndContacts.adapter = addressAndContactsAdapter
        binding.rvAdressAndContacts.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }

    override fun getLayoutResId() = R.layout.fragment_adress_and_contacts
    override val vm: AddressAndContactsVM
        get() = ViewModelProvider(this).get(AddressAndContactsVM::class.java)

}