package uz.medion.ui.main.user.spaMedicine

import android.util.Log
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.data.constants.Keys
import uz.medion.databinding.FragmentSpaMedicineBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.ui.main.MainVM

class SpaMedicineFragment : BaseFragment<FragmentSpaMedicineBinding, MainVM>() {

    private lateinit var adapter: SpaMedicineAdapter

    override fun onBound() {
        setUp()
    }

    fun setUp() {
        adapter = SpaMedicineAdapter { pos ->
            findNavController().navigate(
                R.id.action_spaMedicineFragment_to_spaMedicineDetailsFragment,
                bundleOf(Pair(Keys.BUNDLE_SPA_TYPE, pos))
            )
        }
        binding.rvSpa1.adapter = adapter
        adapter.setData(Constants.getSpaHeaders())
        Log.d("-------------", "setUp: ${Constants.getSpaHeaders()}")
        binding.rvSpa1.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }


    override fun getLayoutResId() = R.layout.fragment_spa_medicine
    override val vm: MainVM
        get() = ViewModelProvider(this).get(MainVM::class.java)

}