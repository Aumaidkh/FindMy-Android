package com.hopcape.findmy

import androidx.test.espresso.IdlingResource
import androidx.test.espresso.IdlingResource.ResourceCallback

class DelayIdlingResource(
    private val delay: Long
): IdlingResource {

    private var resourceCallback: ResourceCallback? = null

    private var startTime: Long = 0

    init {
        startTime = System.currentTimeMillis()
    }

    override fun getName(): String {
        return DelayIdlingResource::class.java.simpleName
    }

    override fun registerIdleTransitionCallback(callback: IdlingResource.ResourceCallback?) {
        this.resourceCallback = callback
    }

    override fun isIdleNow(): Boolean {
        val elapsedTime = System.currentTimeMillis() - startTime
        val idle = elapsedTime >= delay

        if (idle && resourceCallback != null){
            resourceCallback?.onTransitionToIdle()
        }

        return idle
    }
}