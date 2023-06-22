package com.hopcape.findmy.core.utils

import android.app.Activity
import android.graphics.Color
import android.view.View
import android.view.Window
import android.view.WindowManager


fun Activity.setWhiteStatusBar(){ // Set the status bar color to white
    // Set the status bar color to white
    val window: Window = window
    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    window.statusBarColor = Color.WHITE

    // Set the system UI visibility options

    // Set the system UI visibility options
    val decorView: View = window.decorView
    val visibility: Int = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    decorView.setSystemUiVisibility(visibility)
}