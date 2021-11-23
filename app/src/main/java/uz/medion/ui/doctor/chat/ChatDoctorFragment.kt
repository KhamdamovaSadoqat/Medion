package uz.medion.ui.doctor.chat

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uz.medion.R

class ChatDoctorFragment : Fragment() {

    companion object {
        fun newInstance() = ChatDoctorFragment()
    }

    private lateinit var viewModel: ChatDoctorViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.chat_doctor_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ChatDoctorViewModel::class.java)
        // TODO: Use the ViewModel
    }

}