package com.hopcape.findmy.feature_auth.presentation.sign_up

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.hopcape.findmy.R
import com.hopcape.findmy.databinding.FragmentSignUpBinding

/**
 * Used for rendering a screen with email,fullname and password input fields and
 * a button to register a user with those fields and a google login button
 * */
class SignUpFragment : Fragment() {

   private val binding by lazy {
       FragmentSignUpBinding.inflate(layoutInflater)
   }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View { // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClicks()
    }


    private fun setupClicks(){
        binding.tvLogin.setOnClickListener {
            // Navigate user back to the Login Screen
            findNavController().navigateUp()
        }
    }

}