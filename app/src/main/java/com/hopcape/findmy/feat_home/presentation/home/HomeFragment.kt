package com.hopcape.findmy.feat_home.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.NavigationUI
import androidx.navigation.ui.setupWithNavController
import com.hopcape.findmy.R
import com.hopcape.findmy.databinding.FragmentHomeBinding

private const val TAG = "HomeFragment"
/**
 * This fragment is going to host the
 * Report
 * Claims
 * Notifications
 * and Home fragments along with the bottom navigation menu
 * that is set up*/
class HomeFragment: Fragment() {

    private val binding by lazy {
        FragmentHomeBinding.inflate(layoutInflater)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupBottomNavigationMenu()
    }

    private fun setupBottomNavigationMenu(){
        // Define container.
        val loginDialogContainer = childFragmentManager.findFragmentById(R.id.homeFragmentNavHost) as NavHostFragment

        // Set nav controller.
        val loginNavController: NavController = loginDialogContainer.navController


        NavigationUI.setupWithNavController(binding.bottomNav,loginNavController)

    }
}