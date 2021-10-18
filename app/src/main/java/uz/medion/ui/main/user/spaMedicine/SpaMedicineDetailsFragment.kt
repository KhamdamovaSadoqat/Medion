package uz.medion.ui.main.user.spaMedicine

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.data.constants.Keys
import uz.medion.databinding.FragmentSpaMedicineDetailsBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.ui.main.MainVM

class SpaMedicineDetailsFragment: BaseFragment<FragmentSpaMedicineDetailsBinding, MainVM>() {

    private lateinit var adapter: SpaMedicineDetailsAdapter

    override fun onBound() {
        setUp()
    }

    fun setUp(){
        val bundle = this.arguments
        if (bundle != null) {
            val text = bundle.getInt(
                Keys.BUNDLE_SPA_TYPE,
                android.R.attr.defaultValue
            )
            binding.tvSpa2.text = requireContext().getText(text)
        }

        adapter = SpaMedicineDetailsAdapter {  }
        binding.rvSpa1.adapter = adapter
        adapter.setData(Constants.getSpaMassageDetails(), Constants.getSpaMassageTimeDetails())
        binding.rvSpa1.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }


    override fun getLayoutResId() = R.layout.fragment_spa_medicine_details
    override val vm: MainVM
        get() = ViewModelProvider(this).get(MainVM::class.java)

}