package uz.medion.ui.splash.sign_in

import android.annotation.SuppressLint
import android.content.Intent
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import uz.medion.R
import uz.medion.databinding.FragmentSignInBinding
import uz.medion.ui.base.BaseFragment
import uz.medion.ui.main.MainActivity
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task
import uz.medion.data.constants.Constants
import uz.medion.data.constants.Keys


class SignInFragment : BaseFragment<FragmentSignInBinding, SignInVM>() {

    lateinit var mGoogleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 9001


    override fun onBound() {

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestIdToken(Constants.GOOGLE_OAUTH)
            .requestEmail()
            .build()

        mGoogleSignInClient = GoogleSignIn.getClient(requireContext(), gso)

        binding.imgGoogle.setOnClickListener {
            signIn()
        }

        binding.btnSignIn.setOnClickListener {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
        binding.btnSignUp.setOnClickListener {
            findNavController().navigate(R.id.signUpFragment)
        }
    }

    private fun signIn() {
        val signInIntent = mGoogleSignInClient.signInIntent
        startActivityForResult(
            signInIntent, RC_SIGN_IN
        )
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

    @SuppressLint("LogConditional")
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
//            val googleId = account?.id ?: ""
//            val googleFirstName = account?.givenName ?: ""
//            val googleLastName = account?.familyName ?: ""
//            val googleEmail = account?.email ?: ""
//            val googleProfilePicURL = account?.photoUrl.toString()
//            val googleIdToken = account?.idToken ?: ""


        } catch (e: ApiException) {
            // Sign in was unsuccessful
            Log.e(
                "failed code=", e.statusCode.toString()
            )
        }
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