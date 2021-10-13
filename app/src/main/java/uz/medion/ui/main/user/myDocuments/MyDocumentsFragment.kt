package uz.medion.ui.main.user.myDocuments

import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.databinding.FragmentMyDocumentsBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.ui.main.user.aboutDoctor.AboutDoctorAdapter

class MyDocumentsFragment : BaseFragment<FragmentMyDocumentsBinding, MyDocumentsVM>() {

    lateinit var adapterDocuments: MyDocumentsAdapter
    lateinit var adapterDocumentsType: AboutDoctorAdapter

    override fun onBound() {
        setUp()
    }

    fun setUp() {
        adapterDocumentsType = AboutDoctorAdapter { pos, lastPos -> }
        binding.rvDocumentTypes.adapter = adapterDocumentsType
        adapterDocumentsType.setData(Constants.getMyDoctorDocumentsType())
        binding.rvDocumentTypes.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)


        adapterDocuments = MyDocumentsAdapter()
        binding.rvDocuments.adapter = adapterDocuments
        adapterDocuments.setData(Constants.getMyDoctorsDocuments())
        binding.rvDocuments.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
    }

    override fun getLayoutResId() = R.layout.fragment_my_documents

    override val vm: MyDocumentsVM
        get() = ViewModelProvider(this).get(MyDocumentsVM::class.java)

}