package uz.medion.ui.main.user.adressAndContacts

import androidx.lifecycle.ViewModelProvider
//import com.google.android.gms.maps.CameraUpdateFactory
//import com.google.android.gms.maps.GoogleMap
//import com.google.android.gms.maps.OnMapReadyCallback
//import com.google.android.gms.maps.SupportMapFragment
//import com.google.android.gms.maps.model.LatLng
//import com.google.android.gms.maps.model.MarkerOptions
import uz.medion.R
import uz.medion.databinding.FragmentAdressDetailsBinding
import uz.medion.ui.base.BaseFragment


class AddressDetailsFragment : BaseFragment<FragmentAdressDetailsBinding, AdressDetailsVM>()
//    OnMapReadyCallback
{
//
//    private var mMap: GoogleMap? = null
//
    override fun onBound() {
//        val mapFragment: SupportMapFragment = requireActivity().supportFragmentManager.findFragmentById(
//            R.id.fcv_map) as SupportMapFragment
//        mapFragment.getMapAsync(this)
    }
//
//    override fun onMapReady(googleMap: GoogleMap) {
//        mMap = googleMap
//        // Add a marker in Sydney and move the camera
//        // Add a marker in Sydney and move the camera
//        val medionAdress = LatLng(41.3294727, 69.258329)
//        mMap!!.addMarker(MarkerOptions().position(medionAdress).title("Medion"))
//        mMap!!.moveCamera(CameraUpdateFactory.newLatLng(medionAdress))
//    }
//
    override fun getLayoutResId() = R.layout.fragment_adress_details
    override val vm: AdressDetailsVM
        get() = ViewModelProvider(this).get(AdressDetailsVM::class.java)


//
}