package com.hopcape.findmy

import android.os.Looper
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.IdlingRegistry
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import java.util.logging.Handler

@RunWith(AndroidJUnit4ClassRunner::class)
class MainActivityTest {


    @Test
    fun test_isActivityInView() {

        val activityScenario = ActivityScenario.launch(MainActivity::class.java)

        onView(withId(R.id.splashIcon))
            .check(matches(isDisplayed()))
    }

    @Test fun test_LoginActivityInViewAfterDelay() {

        val delayingResource = DelayIdlingResource(4300)

        IdlingRegistry.getInstance().register(delayingResource)

        onView(isRoot()).perform(waitFor(4300))

        onView(withId(R.id.btnLogin)).check(matches(isDisplayed()))

        IdlingRegistry.getInstance().unregister(delayingResource)
    }
}