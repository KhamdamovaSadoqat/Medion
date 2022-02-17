package uz.medion.ui.main.user.adressAndContacts

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import uz.medion.R
import uz.medion.data.constants.Keys.BUNDLE_MAPVIEW
import uz.medion.databinding.FragmentAdressBinding
import uz.medion.utils.ImageDownloader


class AddressFragment : Fragment(),
    OnMapReadyCallback {

    private var mMapView: MapView? = null
    private var googleMap: GoogleMap? = null
    lateinit var binding: FragmentAdressBinding
    private val args: AddressFragmentArgs by navArgs()

//    override fun onBound() {
////        mMapView?.onCreate(this.requireArguments())
////        mMapView = binding.userListMap
////        mMapView!!.getMapAsync(this)
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_adress,
            container,
            false
        )
        setUp()
        initGoogleMap(savedInstanceState)
        return binding.root
    }

    fun setUp() {
        ImageDownloader.loadImage(requireContext(), args.branch!!.imgUrl!!, binding.ivCenterPhoto)
        binding.tvClinicName.text = args.branch!!.title
        binding.tvContact.text = args.branch!!.phone
        binding.tvAddress.text = args.branch!!.address
        binding.tvClinicDescription.text = args.branch!!.context
        binding.tvAddress.setOnClickListener {

        }
    }

    private fun initGoogleMap(savedInstanceState: Bundle?) {

        mMapView = binding.userListMap
        mMapView!!.onCreate(savedInstanceState)
        mMapView!!.onResume()
        mMapView!!.getMapAsync(this)

        try {
            MapsInitializer.initialize(requireContext().applicationContext)
        } catch (e: Exception) {
            Toast.makeText(requireContext(), "Ups something went wrong", Toast.LENGTH_SHORT).show()
            Log.e("----------", "initGoogleMap: $e")
        }
        mMapView!!.getMapAsync { mMap ->
            googleMap = mMap

            // For dropping a marker at a point on the Map
            val medion = LatLng(
                args.branch!!.latitude!!.toDouble(),
                args.branch!!.longitude!!.toDouble()
            )
            googleMap!!.addMarker(
                MarkerOptions().position(medion)
                    .title(args.branch!!.title)
            )

            // For zooming automatically to the location of the marker
            val cameraPosition = CameraPosition.Builder().target(medion).zoom(14f).build()
            googleMap!!.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))
        }
    }

//    override fun getLayoutResId() = R.layout.fragment_adress
//    override val vm: AddressAndContactsVM
//        get() = ViewModelProvider(this).get(AddressAndContactsVM::class.java)

    override fun onMapReady(map: GoogleMap) {
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            )
            != PackageManager.PERMISSION_GRANTED
        ) {
            return
        }
        map.isMyLocationEnabled = true

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        var mapViewBundle = outState.getBundle(BUNDLE_MAPVIEW)
        if (mapViewBundle == null) {
            mapViewBundle = Bundle()
            outState.putBundle(BUNDLE_MAPVIEW, mapViewBundle)
        }
        mMapView!!.onSaveInstanceState(mapViewBundle)
    }

    override fun onResume() {
        super.onResume()
        mMapView!!.onResume()
    }

    override fun onStart() {
        super.onStart()
        mMapView!!.onStart()
    }

    override fun onStop() {
        super.onStop()
        mMapView!!.onStop()
    }

    override fun onPause() {
        mMapView!!.onPause()
        super.onPause()
    }

    override fun onDestroy() {
        mMapView!!.onDestroy()
        super.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mMapView!!.onLowMemory()
    }


}