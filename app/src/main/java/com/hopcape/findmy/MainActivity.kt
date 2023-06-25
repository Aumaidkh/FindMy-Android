package com.hopcape.findmy

import android.animation.ValueAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.firebase.auth.FirebaseAuth
import com.hopcape.findmy.core.utils.setWhiteStatusBar
import com.hopcape.findmy.databinding.ActivityMainBinding
import com.hopcape.findmy.feature_auth.AuthenticationActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var firebaseAuth: FirebaseAuth

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setWhiteStatusBar()
        setContentView(binding.root)
        ValueAnimator.ofFloat(0f,1f,1f,0.5f,1f).apply {
           addUpdateListener { animator ->
               binding.splashIcon.alpha = animator.animatedValue as Float
           }
            duration = 4000
        }.start()
        Handler(Looper.getMainLooper()).postDelayed({
            if (firebaseAuth.currentUser != null){
                launchHomeActivity()
                return@postDelayed
            }
            launchAuthActivity()
        },4200)
    }

    private fun launchAuthActivity(){
        Intent(this@MainActivity,AuthenticationActivity::class.java)
            .also {
                startActivity(it)
                finish()
            }
    }

    private fun launchHomeActivity(){
        Intent(this@MainActivity,HomeActivity::class.java)
            .also {
                startActivity(it)
                finish()
            }
    }
}