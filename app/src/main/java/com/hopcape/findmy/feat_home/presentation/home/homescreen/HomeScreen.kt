package com.hopcape.findmy.feat_home.presentation.home.homescreen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hopcape.findmy.databinding.FragmentHomescreenBinding

private const val TAG = "HomeScreen"
/**
 * This Fragment is going to be the home screen
 * */
class HomeScreen: Fragment() {

    private val binding by lazy {
        FragmentHomescreenBinding.inflate(layoutInflater)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        Log.d(TAG, "onCreateView: HomeScreen")
        return binding.root
    }
}