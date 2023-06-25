package com.hopcape.findmy.feat_home.presentation.home.notifications_screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hopcape.findmy.databinding.FragmentNotificationsBinding

private const val TAG = "NotificationScreen"
/**
 * This Fragment is going to show all the notifications
 *
 * */
class NotificationScreen: Fragment() {

    private val binding by lazy {
        FragmentNotificationsBinding.inflate(layoutInflater)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        Log.d(TAG, "onCreateView: HomeScreen")
        return binding.root
    }
}