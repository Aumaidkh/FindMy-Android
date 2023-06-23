package com.hopcape.findmy.feature_auth.login

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.IntentSenderRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.result.registerForActivityResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.hopcape.findmy.R
import com.hopcape.findmy.core.utils.Resource
import com.hopcape.findmy.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()

    private val binding by lazy {
        FragmentLoginBinding.inflate(layoutInflater)
    }

    private val resultLauncher =
        registerForActivityResult(ActivityResultContracts.StartIntentSenderForResult()) { result ->
            Log.d(TAG, "Result: ${result.resultCode}")
            if (result.resultCode == Activity.RESULT_OK) {
                viewModel.onSignInIntent(result.data)
            }
        }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View { // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView: Opened")
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
            etEmailField.setOnTextChangeListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    viewModel.onEmailChange(p0.toString())
                }

                override fun afterTextChanged(p0: Editable?) = Unit
            })

            // Password Text Watcher
            etPasswordField.setOnTextChangeListener(object : TextWatcher {
                override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) = Unit

                override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                    viewModel.onPasswordChange(p0.toString())
                }

                override fun afterTextChanged(p0: Editable?) = Unit
            })
        }
    }

    private fun setupClicks() { // Google Sign In
        binding.apply {
            btnLogin.setOnClickListener {
                viewModel.login()
            }



            btnGoogleLogin.setOnClickListener {
                viewModel.onGoogleSignIn()
            }

            tvSignUp.setOnClickListener {
                findNavController().navigate(R.id.action_loginFragment_to_signUpFragment)
            }
        }
    }


    private fun consumeFlows() { // Collecting State
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                binding.btnLogin.progressBarVisible(state is LoginViewState.Loading)
                when(state){
                    is LoginViewState.Error -> {
                        Log.d(TAG, "consumeFlows: Error: ${state.error.asString(requireContext())}")
                    }
                    is LoginViewState.Initial -> Unit
                    is LoginViewState.Loading -> {
                        Log.d(TAG, "consumeFlows: Loading...")
                    }
                    is LoginViewState.Success -> {
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
                        Log.d(TAG, "consumeFlows: Sign In With Google")
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
                    etEmailField.apply {
                         setEmailError(state.emailError?.asString(requireContext()))

                    }

                    etPasswordField.apply {
                        setEmailError(state.passwordError?.asString(requireContext()))
                    }
                }
            }
        }
    }
}

private const val TAG = "LoginFragment"