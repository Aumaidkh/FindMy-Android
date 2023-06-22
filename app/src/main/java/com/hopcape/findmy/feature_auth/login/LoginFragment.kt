package com.hopcape.findmy.feature_auth.login

import android.app.Activity
import android.os.Bundle
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
import com.hopcape.findmy.R
import com.hopcape.findmy.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint class LoginFragment : Fragment() {

    private val viewModel: LoginViewModel by viewModels()

    private val binding by lazy {
        FragmentLoginBinding.inflate(layoutInflater)
    }

    private lateinit var resultLauncher: ActivityResultLauncher<IntentSenderRequest>



    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View { // Inflate the layout for this fragment
        Log.d(TAG, "onCreateView: Opened")
        consumeFlows()
        resultLauncher = requireActivity().registerForActivityResult(
            ActivityResultContracts.StartIntentSenderForResult()) { result ->
            Log.d(TAG, "Result: ${result.resultCode}")
            if (result.resultCode == Activity.RESULT_OK){
                viewModel.onSignInIntent(result.data)
            }
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClicks()
    }

    private fun setupClicks(){
        // Google Sign In
        binding.apply {
            btnLogin.setOnClickListener {
                Log.d(TAG, "setupClicks: Button Tapped:")
               // viewModel.onGoogleSignIn()
            }

            btnGoogleLogin.setOnClickListener {
                viewModel.onGoogleSignIn()
            }
        }
    }


    private fun consumeFlows() { // Collecting State
        lifecycleScope.launchWhenStarted {
            viewModel.state.collect { state ->
                when(state){
                    is LoginViewState.Error -> {
                        Log.d(TAG, "consumeFlows: Error: ${state.error.asString(requireContext())}")
                    }
                    is LoginViewState.Initial -> Unit
                    is LoginViewState.Loading -> {
                        Log.d(TAG, "consumeFlows: Loading...")
                    }
                    is LoginViewState.Success -> {
                        Log.d(TAG, "consumeFlows: Logged In")
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
                            resultLauncher.launch(IntentSenderRequest.Builder(event.intentSender
                            ).build())
                        }
                    }
                }
            }
        }
    }
}

private const val TAG = "LoginFragment"