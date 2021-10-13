package uz.medion.ui.main.user.myDocuments

import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.data.model.AboutDoctorItems
import uz.medion.databinding.FragmentMyDocumentsBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.ui.main.user.aboutDoctor.AboutDoctorAdapter
import uz.medion.ui.main.user.aboutDoctor.AboutDoctorCommentAdapter
import java.util.ArrayList

class MyDocumentsFragment : BaseFragment<FragmentMyDocumentsBinding, MyDocumentsVM>() {

    lateinit var adapterDocuments: MyDocumentsAdapter
    lateinit var adapterDocumentsType: AboutDoctorAdapter
    private lateinit var data: ArrayList<AboutDoctorItems>

    override fun onBound() {
        setUp()
    }

    fun setUp() {
        data = Constants.getMyDoctorDocumentsType()
        adapterDocumentsType = AboutDoctorAdapter { position, lastPosition ->
            if (position != lastPosition) {
                data[position] =
                    AboutDoctorItems(
                        data[position].categoryName,
                        R.color.nile_blue_900,
                        R.color.white
                    )
                data[lastPosition] = AboutDoctorItems(
                    data[lastPosition].categoryName,
                    R.color.solitude_50,
                    R.color.tangaroa_900
                )
                adapterDocumentsType.setData(data)
            }
        }
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