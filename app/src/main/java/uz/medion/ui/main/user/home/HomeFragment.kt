package uz.medion.ui.main.user.home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.lifecycle.ViewModelProvider
import uz.medion.R
import uz.medion.databinding.FragmentHomeBinding
import uz.medion.ui.base.BaseFragment

class HomeFragment : BaseFragment<FragmentHomeBinding, HomeVM>() {

    override fun onBound() {
        TODO("Not yet implemented")
    }

    @LayoutRes
    override fun getLayoutResId(): Int =R.layout.fragment_home
    override val vm: HomeVM
        get() = ViewModelProvider(this).get(HomeVM::class.java)

}