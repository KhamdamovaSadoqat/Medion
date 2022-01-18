package uz.medion.ui.main.user.myDoctors

import android.os.Build
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.model.remote.Status
import uz.medion.databinding.FragmentMyDoctorsBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.utils.invisible
import uz.medion.utils.visible

class MyDoctorsFragment : BaseFragment<FragmentMyDoctorsBinding, MyDoctorsVM>() {

    private lateinit var myDoctorsAdapter: MyDoctorsAdapter

    override fun onBound() {
        setUp()
    }

    private fun setUp() {
        myDoctorsAdapter = MyDoctorsAdapter { doctor ->
            setMyDoctorsFavourite(doctor.id)
            getMyDoctors()
        }
        binding.rvMyDoctors.layoutManager =
            LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)
        binding.rvMyDoctors.adapter = myDoctorsAdapter

        getMyDoctors()

        binding.tvFavourites.setOnClickListener {
            binding.tvFavourites.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.bg_blue_8)
            binding.tvAll.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.bg_solitude_8)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                binding.tvAll.setTextColor(requireContext().getColor(R.color.tangaroa_900))
                binding.tvFavourites.setTextColor(requireContext().getColor(R.color.white))
            } else {
                binding.tvFavourites.setTextColor(resources.getColor(R.color.white))
                binding.tvAll.setTextColor(resources.getColor(R.color.tangaroa_900))
            }
            getMyDoctorsFavourite()
        }

        binding.tvAll.setOnClickListener {
            binding.tvFavourites.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.bg_solitude_8)
            binding.tvAll.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.bg_blue_8)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                binding.tvAll.setTextColor(requireContext().getColor(R.color.white))
                binding.tvFavourites.setTextColor(requireContext().getColor(R.color.tangaroa_900))
            } else {
                binding.tvFavourites.setTextColor(resources.getColor(R.color.tangaroa_900))
                binding.tvAll.setTextColor(resources.getColor(R.color.white))
            }
            getMyDoctors()
        }
    }

    private fun getMyDoctors(){
        vm.myDoctors().observe(this) { myDoctors ->
            when (myDoctors.status) {
                Status.LOADING -> {
                    binding.progress.visible()
                }
                Status.SUCCESS -> {
                    binding.progress.invisible()
                    binding.rvMyDoctors.visible()
                    myDoctorsAdapter.setData(myDoctors.data!!)
                }
                Status.ERROR -> {
                    binding.progress.invisible()
                }
            }
        }
    }

    private fun getMyDoctorsFavourite(){
        vm.myDoctorsFavourite().observe(this) { myDoctors ->
            when (myDoctors.status) {
                Status.LOADING -> {
                    binding.progress.visible()
                }
                Status.SUCCESS -> {
                    binding.progress.invisible()
                    binding.rvMyDoctors.visible()
                    myDoctorsAdapter.setData(myDoctors.data!!)
                }
                Status.ERROR -> {
                    binding.progress.invisible()
                }
            }
        }
    }

    private fun setMyDoctorsFavourite(doctorId: Int){
        vm.setDoctorsFavourite(doctorId).observe(this) { myDoctors ->
            when (myDoctors.status) {
                Status.LOADING -> {
                    binding.progress.visible()
                }
                Status.SUCCESS -> {
                    binding.progress.invisible()
                    binding.rvMyDoctors.visible()
                }
                Status.ERROR -> {
                    binding.progress.invisible()
                }
            }
        }
    }

    override fun getLayoutResId() = R.layout.fragment_my_doctors
    override val vm: MyDoctorsVM
        get() = ViewModelProvider(this).get(MyDoctorsVM::class.java)

}