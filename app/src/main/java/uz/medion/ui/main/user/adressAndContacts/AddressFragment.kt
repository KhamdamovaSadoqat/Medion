package uz.medion.ui.main.user.adressAndContacts

import androidx.lifecycle.ViewModelProvider
import uz.medion.R
import uz.medion.databinding.FragmentAdressBinding
import uz.medion.ui.base.BaseFragment

class AddressFragment : BaseFragment<FragmentAdressBinding, AddressAndContactsVM>() {


    override fun onBound() {

    }

    override fun getLayoutResId() = R.layout.fragment_adress
    override val vm: AddressAndContactsVM
        get() = ViewModelProvider(this).get(AddressAndContactsVM::class.java)

}