package uz.medion.ui.main.user.myDoctors

import android.os.Build
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.databinding.FragmentMyDoctorsBinding
import uz.medion.ui.base.BaseFragment

class MyDoctorsFragment : BaseFragment<FragmentMyDoctorsBinding, MyDoctorsVM>() {

    private lateinit var myDoctorsAdapter: MyDoctorsAdapter

    override fun onBound() {
        setUp()
    }

    private fun setUp(){
        myDoctorsAdapter = MyDoctorsAdapter { }
        myDoctorsAdapter.setData(Constants.getMyDoctors())
        binding.rvMyDoctors.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.rvMyDoctors.adapter = myDoctorsAdapter
        
        binding.tvFavourites.setOnClickListener { 
            binding.tvFavourites.background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_blue_8)
            binding.tvAll.background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_solitude_8)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                binding.tvAll.setTextColor(requireContext().getColor(R.color.tangaroa_900))
                binding.tvFavourites.setTextColor(requireContext().getColor(R.color.white))
            } else{
                binding.tvFavourites.setTextColor(resources.getColor(R.color.white))
                binding.tvAll.setTextColor(resources.getColor(R.color.tangaroa_900))
            }
            myDoctorsAdapter.setData(Constants.getMyDoctorsFavourite())
        }

        binding.tvAll.setOnClickListener {
            binding.tvFavourites.background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_solitude_8)
            binding.tvAll.background = ContextCompat.getDrawable(requireContext(), R.drawable.bg_blue_8)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                binding.tvAll.setTextColor(requireContext().getColor(R.color.white))
                binding.tvFavourites.setTextColor(requireContext().getColor(R.color.tangaroa_900))
            } else{
                binding.tvFavourites.setTextColor(resources.getColor(R.color.tangaroa_900))
                binding.tvAll.setTextColor(resources.getColor(R.color.white))
            }

            myDoctorsAdapter.setData(Constants.getMyDoctors())
        }
    }

    override fun getLayoutResId() = R.layout.fragment_my_doctors
    override val vm: MyDoctorsVM
        get() = ViewModelProvider(this).get(MyDoctorsVM::class.java)

}