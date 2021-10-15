package uz.medion.ui.main.user.chooseLanguage

import androidx.lifecycle.ViewModelProvider
import uz.medion.R
import uz.medion.databinding.FragmentChooseLanguageBinding
import uz.medion.ui.base.BaseFragment

class ChooseLanguageFragment : BaseFragment<FragmentChooseLanguageBinding, ChooseLanguageVM>() {


    override fun onBound() {
        binding.chbRussian.setOnClickListener {
            binding.chbEnglish.isChecked = false
            binding.chbUzbek.isChecked = false
        }
        binding.chbUzbek.setOnClickListener {
            binding.chbEnglish.isChecked = false
            binding.chbRussian.isChecked = false
        }
        binding.chbEnglish.setOnClickListener {
            binding.chbRussian.isChecked = false
            binding.chbUzbek.isChecked = false
        }
    }

    override fun getLayoutResId() = R.layout.fragment_choose_language
    override val vm: ChooseLanguageVM
        get() = ViewModelProvider(this).get(ChooseLanguageVM::class.java)

}