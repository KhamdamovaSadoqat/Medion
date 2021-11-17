package uz.medion.ui.main.doctor.personalAccount

import androidx.lifecycle.ViewModelProvider
import uz.medion.R
import uz.medion.databinding.FragmentPersonalAccountBinding
import uz.medion.ui.base.BaseFragment


class PersonalAccountDoctorFragment : BaseFragment<FragmentPersonalAccountBinding, PersonalAccountVM >() {

    override fun onBound() {

    }

    override fun getLayoutResId() = R.layout.fragment_personal_account
    override val vm: PersonalAccountVM
        get() = ViewModelProvider(this).get(PersonalAccountVM::class.java)

}