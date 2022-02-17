package uz.medion.ui.main.doctor.profileDoctor

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.method.KeyListener
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.github.dhaval2404.imagepicker.ImagePicker
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout
import uz.medion.R
import uz.medion.data.model.doctor.DoctorControllerPostResponses
import uz.medion.data.model.doctor.DoctorsControllerPostBody
import uz.medion.data.model.doctor.EduInfo
import uz.medion.data.network.CreateRetrofit
import uz.medion.data.network.NetworkInterceptor
import uz.medion.data.network.RestApi
import uz.medion.databinding.FragmentChangeProfileDoctorBinding
import java.util.*


private const val PROFILE_IMAGE_REQ_CODE = 101
private const val GALLERY_IMAGE_REQ_CODE = 102
private const val CAMERA_IMAGE_REQ_CODE = 103

class ChangeProfileDoctorFragment : Fragment() {
    private var TAG="ChangeProfile"

    private lateinit var binding: FragmentChangeProfileDoctorBinding
    private var mCameraUri: Uri? = null
    private var mGalleryUri: Uri? = null
    private var mProfileUri: Uri? = null
    private var check = true
    private lateinit var listener: KeyListener
    private val calendar= Calendar.getInstance()
    private lateinit var viewModel:ChangeProfileDoctorFragmentViewModel
    private lateinit var request: DoctorsControllerPostBody



    interface PickerOptionListener {
        fun onTakeCameraSelected()
        fun onChooseGallerySelected()
    }

    @SuppressLint("WrongConstant", "SetTextI18n", "LogConditional")
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
        val interceptor = NetworkInterceptor(requireContext()   )
//        val service= CreateRetrofit.getRetrofit(interceptor).create(RestApi::class.java)
//        val factory = ChangeProfileDoctorFactory(service)

        request= DoctorsControllerPostBody(
            binding.birthdayEdit.toString(),
          listOf(),
            binding.nameEdit.toString(),
            "male",
            "htt",
            "1234",
            binding.editPhone.toString(),
            listOf(),
            "kamalov",
            listOf(),
            )

        viewModel=ViewModelProvider(requireActivity()).get(ChangeProfileDoctorFragmentViewModel::class.java)

        viewModel.doctorInfo(request)
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


        binding.name.setEndIconOnClickListener {
            binding.name.isClickable
            endIcon(binding.name,binding.nameEdit)

        }

        binding.editSurname.setEndIconOnClickListener {
            endIcon(binding.editSurname,binding.surnameEdit)
        }
        binding.editMiddleName.setEndIconOnClickListener {
            endIcon(binding.editMiddleName,binding.middleName)
        }
        binding.editBirthday.setEndIconOnClickListener {
            val datePickerDialog = DatePickerDialog(
                requireContext(),
                R.style.MySpinnerDatePickerStyle,
                DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                    // Display Selected date in TextView
                    binding.birthdayEdit.setText("$dayOfMonth.$monthOfYear.$year")
                },
                calendar.get(Calendar.YEAR),
                calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)
            )
            datePickerDialog.show()
            datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE)
                .setTextColor(ContextCompat.getColor(requireContext(), R.color.fire_brick_900))
            datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE)
                .setTextColor(ContextCompat.getColor(requireContext(), R.color.tangaroa_900))

        }
        binding.editPhone.setEndIconOnClickListener {

            endIcon(binding.editPhone,binding.phoneEdit)
        }
        binding.editDoctor.setEndIconOnClickListener {
            endIcon(binding.editDoctor,binding.doctorEdit)
        }
        binding.editStaj.setEndIconOnClickListener {
            endIcon(binding.editStaj,binding.experienceEdit)
        }
        binding.editEducationInformation.setEndIconOnClickListener {
            endIcon(binding.editEducationInformation,binding.education)
        }
        binding.editCurrent.setEndIconOnClickListener {
            endIcon(binding.editCurrent,binding.currentEdit)
        }
        binding.previous.setEndIconOnClickListener {
            endIcon(binding.previous,binding.previousEdit)
        }

        return binding.root

    }


    @SuppressLint("LogConditional")
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


    @SuppressLint("LogConditional")
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

    private fun endIcon(bindingUi: TextInputLayout, bindingEdit: TextInputEditText) {
        listener = bindingEdit.keyListener;
        if (check) {
            bindingEdit.keyListener = listener;
            bindingUi.setEndIconDrawable(R.drawable.ic_check)
            check = false

        } else {
            bindingEdit.keyListener = null
            bindingUi.setEndIconDrawable(R.drawable.ic_edit)
            check = true
        }
    }


}


