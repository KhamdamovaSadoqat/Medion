package uz.medion.ui.main.doctor

import androidx.appcompat.app.AppCompatActivity
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.github.dhaval2404.imagepicker.ImagePicker
import uz.medion.R
import uz.medion.databinding.FragmentChangeProfileDoctorBinding


private const val PROFILE_IMAGE_REQ_CODE = 101
private const val GALLERY_IMAGE_REQ_CODE = 102
private const val CAMERA_IMAGE_REQ_CODE = 103

class ChangeProfileDoctorFragment : Fragment() {

    private lateinit var binding: FragmentChangeProfileDoctorBinding
    private var mCameraUri: Uri? = null
    private var mGalleryUri: Uri? = null
    private var mProfileUri: Uri? = null

    interface PickerOptionListener {
        fun onTakeCameraSelected()
        fun onChooseGallerySelected()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_change_profile_doctor,
            container,
            false
        )

        binding.imgPickImage.setOnClickListener {
            showImagePickerOptions(requireContext(), object : PickerOptionListener {
                override fun onTakeCameraSelected() {
                    pickCameraImage()
                }

                override fun onChooseGallerySelected() {
                    pickGalleryImage()
                }
            })
        }
        binding.editPhone.setEndIconOnClickListener {
            binding.editPhone.setEndIconDrawable(R.drawable.ic_fi_camera)
        }

        return binding.root

    }


    @Suppress("UNUSED_PARAMETER")
    fun pickProfileImage(view: View) {
        ImagePicker.with(this)
            .galleryOnly()
            .cropSquare()
            .setImageProviderInterceptor { imageProvider -> // Intercept ImageProvider
                Log.d("ImagePicker", "Selected ImageProvider: " + imageProvider.name)
            }
            .setDismissListener {
                Log.d("ImagePicker", "Dialog Dismiss")
            }
            .maxResultSize(200, 200)
            .start(PROFILE_IMAGE_REQ_CODE)
    }

    fun pickGalleryImage() {
        ImagePicker.with(this)
            .cropSquare()
            // Crop Image(User can choose Aspect Ratio)

            // User can only select image from Gallery
            .galleryOnly()

            .galleryMimeTypes( // no gif images at all
                mimeTypes = arrayOf(
                    "image/png",
                    "image/jpg",
                    "image/jpeg"
                )
            )
            // Image resolution will be less than 1080 x 1920
            .maxResultSize(1080, 1920)
            // .saveDir(getExternalFilesDir(null)!!)
            .start(GALLERY_IMAGE_REQ_CODE)


    }


    @Suppress("UNUSED_PARAMETER")
    fun pickCameraImage() {
        ImagePicker.with(this)
            // User can only capture image from Camera
            .cropSquare()
            .cameraOnly()
            .setImageProviderInterceptor { imageProvider -> // Intercept ImageProvider
                Log.d("ImagePicker", "Selected ImageProvider: " + imageProvider.name)
            }
            .setDismissListener {
                Log.d("ImagePicker", "Dialog Dismiss")
            }
            .start(CAMERA_IMAGE_REQ_CODE)
    }

    private fun showImagePickerOptions(context: Context, listener: PickerOptionListener) {
        // setup the alert builder
        val builder = AlertDialog.Builder(context)
        builder.setTitle(context.getString(R.string.lbl_set_profile_photo))

        // add a list
        val animals = arrayOf<String>(
            context.getString(R.string.lbl_take_camera_picture),
            context.getString(R.string.lbl_choose_from_gallery)
        )
        builder.setItems(
            animals
        ) { dialog: DialogInterface?, which: Int ->
            when (which) {
                0 -> listener.onTakeCameraSelected()
                1 -> listener.onChooseGallerySelected()
            }
        }

        // create and show the alert dialog
        val dialog = builder.create()
        dialog.show()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (resultCode) {
            AppCompatActivity.RESULT_OK -> {
                // Uri object will not be null for RESULT_OK
                val uri: Uri = data?.data!!
                when (requestCode) {
                    PROFILE_IMAGE_REQ_CODE -> {
                        mProfileUri = uri
                        binding.imgDoctor.setImageURI(uri)
                    }
                    GALLERY_IMAGE_REQ_CODE -> {
                        mGalleryUri = uri
                        binding.imgDoctor.setImageURI(uri)
                    }
                    CAMERA_IMAGE_REQ_CODE -> {
                        mCameraUri = uri
                        binding.imgDoctor.setImageURI(uri)
                    }
                }
            }
            ImagePicker.RESULT_ERROR -> {
                Toast.makeText(requireContext(), ImagePicker.getError(data), Toast.LENGTH_SHORT)
                    .show()
            }
            else -> {
                Toast.makeText(requireContext(), "Task Cancelled", Toast.LENGTH_SHORT).show()
            }
        }
    }


}


