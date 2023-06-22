package com.hopcape.findmy.core.utils

import android.content.Context
import androidx.annotation.StringRes


/**
 * This class is to be used as a wrapper around the Strings
 * and String resource ids. Since getting reference to a string
 * resource in a view model requires context therefore we are wrapping the
 * actual res ids too inside this class so that they can be referred without
 * use of context*/
sealed class UiText {
    /**
     * If a String is dynamic string , eg a throwable exception message
     * @param value - carries the message
     * */
    data class Dynamic(val value: String): UiText()

    /**
     * If the string is a res id e.g no internet connection message
     * @param resId - contains the resource id of the string resource
     * @param args - can be var agrs if the resId is a formatted string
     * */
    class StringResource(
        @StringRes val resId: Int,
        vararg val args: Any
    ): UiText()


    /**
     * Maps the UiText to an original string value
     * like the above function the only difference is that the below method
     * is not a composable function
     * */
    fun asString(context: Context): String {
        return when(this){
            is Dynamic -> this.value
            is StringResource -> context.resources.getString(resId,*args)
        }
    }
}