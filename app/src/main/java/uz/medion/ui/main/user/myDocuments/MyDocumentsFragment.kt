package uz.medion.ui.main.user.myDocuments

import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.youtube.player.internal.v
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.data.model.AboutDoctorItems
import uz.medion.databinding.DialogMyDocumentsBinding
import uz.medion.databinding.FragmentMyDocumentsBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.ui.main.user.aboutDoctor.AboutDoctorAdapter
import java.util.*


class MyDocumentsFragment : BaseFragment<FragmentMyDocumentsBinding, MyDocumentsVM>() {

    lateinit var bindingDialog: DialogMyDocumentsBinding
    lateinit var adapterDocuments: MyDocumentsAdapter
    lateinit var adapterDocumentsType: AboutDoctorAdapter
    private lateinit var data: ArrayList<AboutDoctorItems>

    override fun onBound() {
        val inflater =
            view?.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        bindingDialog = DataBindingUtil.inflate<DialogMyDocumentsBinding>(
            inflater, R.layout.dialog_my_documents,
            view?.rootView as ViewGroup?, false
        )

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


        binding.clAdd.setOnClickListener {
            onButtonShowPopupWindowClick(it)
        }
    }

    private fun onButtonShowPopupWindowClick(view: View?) {

        val width = LinearLayout.LayoutParams.MATCH_PARENT
        val height = LinearLayout.LayoutParams.WRAP_CONTENT
        val focusable = true // lets taps outside the popup also dismiss it
        val popupWindow = PopupWindow(bindingDialog.root, width, height, focusable)

        popupWindow.showAtLocation(view, Gravity.BOTTOM, 100, 0)
        bindingDialog.btnCancel.setOnClickListener {
            popupWindow.dismiss()
        }
    }


    override fun getLayoutResId() = R.layout.fragment_my_documents

    override val vm: MyDocumentsVM
        get() = ViewModelProvider(this).get(MyDocumentsVM::class.java)

}