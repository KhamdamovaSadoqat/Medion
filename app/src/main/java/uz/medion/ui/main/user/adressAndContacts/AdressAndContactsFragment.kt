package uz.medion.ui.main.user.adressAndContacts

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.databinding.FragmentAdressAndContactsBinding
import uz.medion.ui.base.BaseFragment

class AdressAndContactsFragment : BaseFragment<FragmentAdressAndContactsBinding, AdressAndContactsVM>() {

    lateinit var adressAndContactsAdapter: AdressAndContactsAdapter

    override fun onBound() {
        setUp()
    }

    fun setUp(){
        adressAndContactsAdapter = AdressAndContactsAdapter {  }
        adressAndContactsAdapter.setData(Constants.getAdressAndContact())
        binding.rvAdressAndContacts.adapter = adressAndContactsAdapter
        binding.rvAdressAndContacts.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }

    override fun getLayoutResId() = R.layout.fragment_adress_and_contacts
    override val vm: AdressAndContactsVM
        get() = ViewModelProvider(this).get(AdressAndContactsVM::class.java)

}