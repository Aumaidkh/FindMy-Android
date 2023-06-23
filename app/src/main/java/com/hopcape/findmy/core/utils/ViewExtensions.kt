package com.hopcape.findmy.core.utils

import android.R
import android.app.Activity
import android.view.View
import com.google.android.material.snackbar.Snackbar

/**
 * Shows a snackbar with message*/
fun Activity.showSnackBar(message: UiText?){
    val view = findViewById<View>(R.id.content)
    Snackbar.make(view,message?.asString(this).toString(),Snackbar.LENGTH_SHORT).show()
}