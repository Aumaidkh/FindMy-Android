package com.hopcape.findmy

import android.view.View
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers.isRoot
import org.hamcrest.Matcher

fun waitFor(millis: Long) = object : ViewAction{
    override fun getDescription(): String {
        return "Wait for $millis milliseconds.";
    }

    override fun getConstraints(): Matcher<View> {
        return isRoot()
    }

    override fun perform(uiController: UiController?, view: View?) {
        uiController?.loopMainThreadForAtLeast(millis);
    }
}