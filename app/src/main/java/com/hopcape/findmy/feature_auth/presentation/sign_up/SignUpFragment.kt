package com.hopcape.findmy.feature_auth.presentation.sign_up

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.google.android.material.snackbar.Snackbar
import com.hopcape.findmy.core.utils.showSnackBar
import com.hopcape.findmy.databinding.FragmentSignUpBinding
import com.hopcape.findmy.feature_auth.presentation.login.UiEvents
import dagger.hilt.android.AndroidEntryPoint

private const val TAG = "SignUpFragment"
/**
 * Used for rendering a screen with email,fullname and password input fields and
 * a button to register a user with those fields and a google login button
 * */
@AndroidEntryPoint
class SignUpFragment : Fragment() {

    // Binding Vars
   private val binding by lazy {
       FragmentSignUpBinding.inflate(layoutInflater)
   }

    // ViewModel
    private val viewModel: SignUpViewModel by viewModels()

    // Google Sign In Result Launcher
    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
             if (result.resultCode == Activity.RESULT_OK) {
                viewModel.onSignInIntent(result.data)
            }
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View { // Inflate the layout for this fragment
        consumeFlows()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClicks()
        setupInputFields()

    }

    private fun setupInputFields() {
        binding.apply { // Email Text Watcher
            emailField.setOnTextChangeListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    viewModel.onEmailChange(p0.toString())
                }

                override fun afterTextChanged(p0: Editable?) = Unit
            })

            // Password Text Watcher
            passwordField.setOnTextChangeListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    viewModel.onPasswordChange(p0.toString())
                }

                override fun afterTextChanged(p0: Editable?) = Unit
            })

            // Full Name Text Watcher
            fullNameLayout.setOnTextChangeListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    viewModel.onFullNameChange(p0.toString())
                }

                override fun afterTextChanged(p0: Editable?) = Unit
            })
        }
    }

    private fun setupClicks(){
        binding.apply {
            // Login
            tvLogin.setOnClickListener {
                // Navigate user back to the Login Screen
                findNavController().navigateUp()
            }
            //Register
            btnRegister.setOnClickListener {
                viewModel.onRegister()
            }

            // Google Login
            btnGoogleLogin.setOnClickListener {
                viewModel.onGoogleSignIn()
            }
        }
    }

    private fun consumeFlows(){
        // Collecting View State
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                binding.btnRegister.progressBarVisible(state is SignUpViewState.Loading)
                when(state){
                    is SignUpViewState.Error -> {
                        requireActivity().showSnackBar(state.error)
                    }
                    is SignUpViewState.Initial -> Unit
                    is SignUpViewState.Loading -> Unit
                    is SignUpViewState.Success -> {
                        Log.d(TAG, "consumeFlows: Logged In :${state.user}")
                    }
                }
            }
        }

        // Collecting Events
        lifecycleScope.launchWhenStarted {
            viewModel.eventFlow.collect { event ->
                when(event){
                    is UiEvents.Error -> {
                        Log.d(TAG, "consumeFlows: Error event: ${event.message.asString(requireContext())}")
                    }
                    is UiEvents.Navigate -> {
                        Log.d(TAG, "consumeFlows: Navigating to ${event.screen}")
                    }
                    is UiEvents.SignInWithGoogle -> {
                        event.intentSender?.let {
                            resultLauncher.launch(IntentSenderRequest.Builder(event.intentSender).build())
                        }
                    }
                }
            }
        }

        // Collecting Form State
        lifecycleScope.launchWhenStarted {
            viewModel.formState.collect { state ->
                binding.apply {
                    emailField.apply {
                        setEmailError(state.emailError?.asString(requireContext()))

                    }

                    passwordField.apply {
                        setEmailError(state.passwordError?.asString(requireContext()))
                    }

                    fullNameLayout.apply {
                        setEmailError(state.fullNameError?.asString(requireContext()))
                    }
                }
            }
        }
    }

}