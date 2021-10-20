package uz.medion.ui.main.user.adressAndContacts

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.databinding.FragmentAdressAndContactsBinding
import uz.medion.ui.base.BaseFragment

class AddressAndContactsFragment : BaseFragment<FragmentAdressAndContactsBinding, AddressAndContactsVM>() {

    lateinit var adressAndContactsAdapter: AddressAndContactsAdapter

    override fun onBound() {
        setUp()

    }

    private fun setUp(){
        adressAndContactsAdapter = AddressAndContactsAdapter {
//            findNavController().navigate(R.id.action_adressAndContactsFragment_to_addressDetailsFragment)
        }
        adressAndContactsAdapter.setData(Constants.getAddressAndContact())
        binding.rvAdressAndContacts.adapter = adressAndContactsAdapter
        binding.rvAdressAndContacts.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }

    override fun getLayoutResId() = R.layout.fragment_adress_and_contacts
    override val vm: AddressAndContactsVM
        get() = ViewModelProvider(this).get(AddressAndContactsVM::class.java)

}