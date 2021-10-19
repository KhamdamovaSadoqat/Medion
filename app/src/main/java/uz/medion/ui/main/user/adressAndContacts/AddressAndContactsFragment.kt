package uz.medion.ui.main.user.adressAndContacts

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.databinding.FragmentAdressAndContactsBinding
import uz.medion.ui.base.BaseFragment

class AddressAndContactsFragment : BaseFragment<FragmentAdressAndContactsBinding, AddressAndContactsVM>() {

    lateinit var addressAndContactsAdapter: AddressAndContactsAdapter

    override fun onBound() {
        setUp()
    }

    fun setUp(){
        addressAndContactsAdapter = AddressAndContactsAdapter {  }
        addressAndContactsAdapter.setData(Constants.getAdressAndContact())
        binding.rvAdressAndContacts.adapter = addressAndContactsAdapter
        binding.rvAdressAndContacts.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }

    override fun getLayoutResId() = R.layout.fragment_adress_and_contacts
    override val vm: AddressAndContactsVM
        get() = ViewModelProvider(this).get(AddressAndContactsVM::class.java)

}