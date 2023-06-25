package com.hopcape.findmy

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hopcape.findmy.core.utils.setWhiteStatusBar
import com.hopcape.findmy.databinding.ActivityHomeBinding
import dagger.hilt.android.AndroidEntryPoint

/**
 * This activity is going to host the entire app. The fragments will be shuffled in this activity
 * Following are some of the fragments which it is going to host
 * 1. Home Fragment : Itself will host ( Home, Report, Claims, Notifications ) Fragments
 * 2. Profile Fragment
 * 3. Item Details Fragment
 * 4. Explore Fragment : Containing Map View
 * etc*/
@AndroidEntryPoint
class HomeActivity : AppCompatActivity() {

    /**
     * Binding Vars*/
    private val binding by lazy {
        ActivityHomeBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setWhiteStatusBar()
        setContentView(binding.root)
    }
}