package com.hopcape.findmy.feature_auth.presentation.login

import android.content.IntentSender
import com.hopcape.findmy.core.utils.UiText

sealed interface UiEvents {

    data class Error(
        val message: UiText
    ): UiEvents

    data class Navigate(val screen: String=""): UiEvents

    data class SignInWithGoogle(val intentSender: IntentSender?): UiEvents

}