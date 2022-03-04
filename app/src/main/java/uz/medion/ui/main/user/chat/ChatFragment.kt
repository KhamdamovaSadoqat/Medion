package uz.medion.ui.main.user.chat

import android.content.Intent
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.data.model.MessageRequest
import uz.medion.data.model.remote.Status
import uz.medion.databinding.FragmentChatBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.utils.invisible
import uz.medion.utils.visible

class ChatFragment : BaseFragment<FragmentChatBinding, ChatVM>() {

    private lateinit var chatAdapter: ChatAdapter

    override fun onBound() {
        setUp()
        getChatMessages(1)
    }

    fun setUp() {
        chatAdapter = ChatAdapter()
        binding.rvChat.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.rvChat.adapter = chatAdapter
        chatAdapter.setUserId(prefs.userId)

        binding.ivFiles.setOnClickListener {
            val intent = Intent()
                .setType("*/*")
                .setAction(Intent.ACTION_GET_CONTENT)
            startActivityForResult(Intent.createChooser(intent, "Select a file"),
                Constants.SELECT_IMAGE)
        }

        binding.editTextTextPersonName.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(editText: Editable?) {
                binding.ivSend.setOnClickListener {
                    Log.d("----------", "setUp: sending")
                    if (binding.editTextTextPersonName.text!!.isNotEmpty() && binding.editTextTextPersonName.text!!.isNotBlank()) {
                        Log.d("----------", "setUp: not empty")
                        postMessage(MessageRequest(3,
                            binding.editTextTextPersonName.text.toString()))
                        binding.editTextTextPersonName.text!!.clear()
                        getChatMessages(1)
                    }
                }
            }
        })
    }

    private fun postMessage(messageRequest: MessageRequest) {
        vm.postChat(messageRequest).observe(this) {
            when (it.status) {
                Status.LOADING -> {}
                Status.ERROR -> Toast.makeText(requireContext(),
                    "somethin went wrong",
                    Toast.LENGTH_SHORT).show()
                Status.SUCCESS -> {
                    Log.d("----------", "postMessage: success")
                    binding.editTextTextPersonName.text!!.clear()
                    binding.editTextTextPersonName.setText("")
                }
            }
        }
    }

    private fun getChatMessages(chatId: Int){
        vm.getChatMessages(chatId).observe(this){
            when (it.status) {
                Status.LOADING -> {binding.progress.visible()}
                Status.ERROR -> {
                    binding.progress.invisible()
                    Toast.makeText(requireContext(),
                        "somethin went wrong",
                        Toast.LENGTH_SHORT).show()
                }
                Status.SUCCESS -> {
                    binding.progress.invisible()
                    Log.d("----------", "postMessage: success")
                    if(it.data?.isEmpty() != true)
                    chatAdapter.setData(it.data!!)
                    binding.rvChat.smoothScrollToPosition(it.data.size)
                }
            }
        }
    }

    override fun getLayoutResId(): Int = R.layout.fragment_chat

    override val vm: ChatVM
        get() = ViewModelProvider(this).get(ChatVM::class.java)

    override fun onDestroyView() {
        super.onDestroyView()
        hideKeyboard()
    }
}
