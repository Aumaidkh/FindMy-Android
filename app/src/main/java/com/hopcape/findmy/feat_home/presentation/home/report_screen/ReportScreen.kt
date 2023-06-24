package com.hopcape.findmy.feat_home.presentation.home.report_screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.hopcape.findmy.databinding.FragmentReportBinding

private const val TAG = "ReportScreen"
/**
 * This Fragment is going to let a user report any lost or found items
 *
 * */
class ReportScreen: Fragment() {
    private val binding by lazy {
        FragmentReportBinding.inflate(layoutInflater)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        Log.d(TAG, "onCreateView: HomeScreen")
        return binding.root
    }
}