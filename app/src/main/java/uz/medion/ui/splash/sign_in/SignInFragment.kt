package uz.medion.ui.splash.sign_in

import android.content.Intent
import android.text.method.HideReturnsTransformationMethod
import android.text.method.PasswordTransformationMethod
import android.util.Log
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import uz.medion.R
import uz.medion.data.constants.Constants
import uz.medion.data.constants.Keys
import uz.medion.data.model.Login
import uz.medion.data.model.TokenDecoded
import uz.medion.data.model.remote.Status
import uz.medion.databinding.FragmentSignInBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.ui.main.MainActivity
import uz.medion.ui.main.doctor.DoctorActivity
import uz.medion.utils.JWTUtils

//login
class SignInFragment : BaseFragment<FragmentSignInBinding, SignInVM>() {

    lateinit var mGoogleSignInClient: GoogleSignInClient
    private val jwtDecoded = JWTUtils()
    var isShowing: Boolean = false

    override fun onBound() {
        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(Constants.GOOGLE_OAUTH)
            .requestEmail()
            .build()
        mGoogleSignInClient = GoogleSignIn.getClient(requireContext(), gso)
        setUp()
    }

    private fun setUp() {
        binding.imgGoogle.setOnClickListener {
            signIn()
        }
        binding.ivShowPassword.setOnClickListener {
            if (isShowing) {
                binding.ivShowPassword.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_eye_slash
                    )
                )
                binding.etPassword.transformationMethod =
                    HideReturnsTransformationMethod.getInstance()
                isShowing = false
            } else {
                binding.ivShowPassword.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_eye
                    )
                )
                binding.etPassword.transformationMethod = PasswordTransformationMethod.getInstance()
                isShowing = true
            }
        }
        //login
        binding.btnSignIn.setOnClickListener {
            if (checkAllFields()) {
                vm.login(Login(binding.etPassword.text.toString(),
                    binding.etPhoneNumber.text.toString()))
                    .observe(this) { response ->
                        when (response.status) {
                            Status.LOADING -> {
                                Log.d("----------", "setUp: loading")
                            }
                            Status.SUCCESS -> {
                                prefs.isRegistered = true
                                prefs.password = binding.etPassword.text.toString().trim()
                                prefs.accessToken = response.data?.accessToken
                                prefs.refreshToken = response.data?.refreshToken
                                Constants.setUnAuthorized(false)

                                val decodedToken = gson.fromJson(jwtDecoded.decoded(response.data?.accessToken!!), TokenDecoded::class.java)
                                if(decodedToken.roles[0] == "CLIENT"){
                                    // starting new activity and ending the login
                                    val intent = Intent(requireContext(), MainActivity::class.java)
                                    startActivity(intent)
                                }else if(decodedToken.roles[0] == "ADMIN"){
                                    //start doctor activity
                                    val intent = Intent(requireContext(), DoctorActivity::class.java)
                                    startActivity(intent)
                                }else{
                                   // case for admin too
                                }
                                requireActivity().finish()
                            }
                            Status.ERROR -> {
                                Log.e("----------", "error: ${response.message}")
                            }
                        }
                    }
            }
        }

        //registration
        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.signUpFragment)
        }

    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(signInIntent, Keys.REQUEST_CODE_SIGN_IN)
    }

    private fun signOut() {
        mGoogleSignInClient.signOut()
            .addOnCompleteListener(requireActivity()) {
                // Update your UI here
            }
    }

    private fun revokeAccess() {
        mGoogleSignInClient.revokeAccess()
            .addOnCompleteListener(requireActivity()) {
                // Update your UI here
            }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(
                ApiException::class.java
            )
            // Signed in successfully
            val action = SignInFragmentDirections.actionSignInFragmentToSignUpFragment(
                account.givenName,
                account.familyName,
                account.id
            )
            findNavController().navigate(action)

        } catch (e: ApiException) {
            // Sign in was unsuccessful
            Log.e(
                "failed code=", e.statusCode.toString()
            )
        }
    }

    private fun checkAllFields(): Boolean {
        if (binding.etPhoneNumber.length() == 0 || android.util.Patterns.EMAIL_ADDRESS.matcher(
                binding.etPhoneNumber.toString())
                .matches()
        ) {
            binding.etPhoneNumber.error = requireContext().getText(R.string.invalid_email)
            return false
        } else binding.etPhoneNumber.error = null
        if (binding.etPassword.length() == 0) {
            binding.etPassword.error = requireContext().getString(R.string.required_field)
            return false
        } else binding.etPassword.error = null
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == Keys.REQUEST_CODE_SIGN_IN) {
            val task =
                GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    override fun getLayoutResId() = R.layout.fragment_sign_in
    override val vm: SignInVM
        get() = ViewModelProvider(this).get(SignInVM::class.java)

}