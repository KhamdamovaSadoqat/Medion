package uz.medion.ui.main.user.personalDate

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import uz.medion.R
import uz.medion.databinding.FragmentPersonalDateBinding
import uz.medion.ui.base.BaseFragment

class PersonalDateFragment : BaseFragment<FragmentPersonalDateBinding, PersonalDateVM>() {


    override fun onBound() {

    }

    override fun getLayoutResId() = R.layout.fragment_personal_date
    override val vm: PersonalDateVM
        get() = ViewModelProvider(this).get(PersonalDateVM::class.java)


}