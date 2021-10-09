package uz.medion.ui.main.user.chooseLanguage

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import uz.medion.R
import uz.medion.databinding.FragmentChooseLanguageBinding
import uz.medion.ui.base.BaseFragment

class ChooseLanguageFragment : BaseFragment<FragmentChooseLanguageBinding, ChooseLanguageVM>() {


    override fun onBound() {

    }

    override fun getLayoutResId() = R.layout.fragment_choose_language
    override val vm: ChooseLanguageVM
        get() = ViewModelProvider(this).get(ChooseLanguageVM::class.java)

}