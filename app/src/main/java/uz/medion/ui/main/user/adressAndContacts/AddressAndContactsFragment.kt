package uz.medion.ui.main.user.adressAndContacts

import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.databinding.FragmentAdressAndContactsBinding
import uz.medion.ui.base.BaseFragment

class AddressAndContactsFragment : BaseFragment<FragmentAdressAndContactsBinding, AdressAndContactsVM>() {

    lateinit var adressAndContactsAdapter: AddressAndContactsAdapter

    override fun onBound() {
        setUp()

    }

    private fun setUp(){
        adressAndContactsAdapter = AddressAndContactsAdapter {
            findNavController().navigate(R.id.action_adressAndContactsFragment_to_addressDetailsFragment)
        }
        adressAndContactsAdapter.setData(Constants.getAdressAndContact())
        binding.rvAdressAndContacts.adapter = adressAndContactsAdapter
        binding.rvAdressAndContacts.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }

    override fun getLayoutResId() = R.layout.fragment_adress_and_contacts
    override val vm: AdressAndContactsVM
        get() = ViewModelProvider(this).get(AdressAndContactsVM::class.java)

}