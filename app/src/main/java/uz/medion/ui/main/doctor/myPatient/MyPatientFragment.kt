package uz.medion.ui.main.doctor.myPatient

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import uz.medion.R
import uz.medion.data.model.remote.Status
import uz.medion.databinding.FragmentMyPtientBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.utils.invisible
import uz.medion.utils.visible

class MyPatientFragment : BaseFragment<FragmentMyPtientBinding,MyPatientViewModel>() {

    private lateinit var adapter: MyPatientAdapter
    private var TAG="MyPatientFragment"






    private fun getFavourite() {
        vm.getFavourite().observe(viewLifecycleOwner) { myPatient ->
            when (myPatient.status) {
                Status.LOADING -> {
                    binding.progress.visible()
                }
                Status.SUCCESS -> {
                    binding.progress.invisible()
                    binding.rvMyPatient.visible()
                    adapter.setData(myPatient.data!!)
                }
            }

        }
    }

    @SuppressLint("LogConditional")
    private fun getPatient() {
        vm.myPatient().observe(this) { myPatient ->
            Log.d(TAG, "getPatient: ${myPatient.data}")
            when (myPatient.status) {
                Status.LOADING -> {
                    binding.progress.visible()
                }
                Status.SUCCESS -> {
                    binding.progress.invisible()
                    binding.rvMyPatient.visible()
                    adapter.setData(myPatient.data!!)
                }
                Status.ERROR -> {
                    binding.progress.invisible()
                }
            }

        }
    }

    private fun setMyPatientFavourite(id: Int) {
        vm.setPatientFavourite(id).observe(viewLifecycleOwner) { myPatient ->
            when (myPatient.status) {
                Status.LOADING -> {
                    binding.progress.visible()
                }
                Status.SUCCESS -> {
                    binding.progress.invisible()
                    binding.rvMyPatient.visible()
                }
                Status.ERROR -> {
                    binding.progress.invisible()
                }
            }
        }
    }

    override fun getLayoutResId()=R.layout.fragment_my_ptient
    override fun onBound() {
        setUp()
    }
    fun setUp(){
        adapter= MyPatientAdapter { patient->
            setMyPatientFavourite(patient.id!!)
            getPatient()
        }
        binding.rvMyPatient.layoutManager=
            LinearLayoutManager(requireContext(),RecyclerView.VERTICAL,false)
        binding.rvMyPatient.adapter=adapter
        getPatient()
        binding.tvFavouritePatient.setOnClickListener {
            binding.tvFavouritePatient.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.bg_blue_8)
            binding.tvAllPatients.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.bg_solitude_8)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                binding.tvAllPatients.setTextColor(requireContext().getColor(R.color.tangaroa_900))
                binding.tvFavouritePatient.setTextColor(requireContext().getColor(R.color.white))
            } else {
                binding.tvFavouritePatient.setTextColor(resources.getColor(R.color.white))
                binding.tvAllPatients.setTextColor(resources.getColor(R.color.tangaroa_900))
            }

            getFavourite()
        }
        binding.tvAllPatients.setOnClickListener {
            binding.tvFavouritePatient.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.bg_solitude_8)
            binding.tvAllPatients.background =
                ContextCompat.getDrawable(requireContext(), R.drawable.bg_blue_8)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                binding.tvAllPatients.setTextColor(requireContext().getColor(R.color.white))
                binding.tvFavouritePatient.setTextColor(requireContext().getColor(R.color.tangaroa_900))
            } else {
                binding.tvFavouritePatient.setTextColor(resources.getColor(R.color.tangaroa_900))
                binding.tvAllPatients.setTextColor(resources.getColor(R.color.white))
            }
            getPatient()

        }

    }

    override val vm: MyPatientViewModel
        get() = ViewModelProvider(this).get(MyPatientViewModel::class.java)
}