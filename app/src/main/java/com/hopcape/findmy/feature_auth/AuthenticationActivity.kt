package com.hopcape.findmy.feature_auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hopcape.findmy.core.utils.setWhiteStatusBar
import com.hopcape.findmy.databinding.ActivityAuthBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * This activity has the responsible for hosting fragments which are responsible for authenticating the user
 * It may be logging in the user, signing the user up, verifying otp or resetting password for the account
 * */
@AndroidEntryPoint
class AuthenticationActivity : AppCompatActivity() {

    /**
     * Binding Vars*/
    private val binding by lazy {
        ActivityAuthBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setWhiteStatusBar()
        setContentView(binding.root)
    }
}