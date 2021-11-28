package uz.medion.ui.main.user.chat

import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import androidx.lifecycle.ViewModelProvider
import butterknife.OnTextChanged
import uz.medion.R
import uz.medion.databinding.FragmentChatBinding
import uz.medion.ui.base.BaseFragment
import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.youtube.player.internal.v
import uz.medion.data.constants.Constants
import uz.medion.data.model.ChatMessageItem
import uz.medion.ui.base.BaseActivity
import uz.medion.ui.main.MainActivity


class ChatFragment : BaseFragment<FragmentChatBinding, ChatVM>()  {

      private lateinit var chatAdapter: ChatAdapter



    override fun onBound() {
        setUp()
    }

    fun setUp() {


        binding.ivFiles.setOnClickListener {

            val intent = Intent()
                .setType("*/*")
                .setAction(Intent.ACTION_GET_CONTENT)

            startActivityForResult(Intent.createChooser(intent, "Select a file"), Constants.SELECT_IMAGE)
        }

            binding.editTextTextPersonName.addTextChangedListener(object : TextWatcher{
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

                }

                override fun afterTextChanged(editText: Editable?) {
                    if (binding.editTextTextPersonName.text.toString().trim().isNotEmpty()) {
                        uz.medion.utils.ViewUtils.fadeOut(binding.ivMicraphone)
                        uz.medion.utils.ViewUtils.fadeIn(binding.ivSend)

                        binding.ivSend.setOnClickListener {
//                            chatAdapter=ChatAdapter()
//                            binding.rvChat.layoutManager =
//                                LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
//                            binding.rvChat.adapter = chatAdapter

                        }
                    }else{
                        uz.medion.utils.ViewUtils.fadeOut(binding.ivSend)
                        uz.medion.utils.ViewUtils.fadeIn(binding.ivMicraphone)
                }

                }

            })




    }


    override fun getLayoutResId(): Int = R.layout.fragment_chat

    override val vm: ChatVM
        get() = ViewModelProvider(this).get(ChatVM::class.java)



    override fun onDestroyView() {
        super.onDestroyView()
        hideKeyboard()
    }
}
