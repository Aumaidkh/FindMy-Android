package com.hopcape.findmy.core.utils

import android.content.Context
import android.content.res.ColorStateList
import android.view.View
import android.widget.Button
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.google.android.material.button.MaterialButton
import com.hopcape.findmy.R
import kotlin.math.abs

class CustomLayoutManager(context: Context,orientation: Int,reverseLayout: Boolean): LinearLayoutManager(context,orientation,reverseLayout) {

    private val mShrinkAmount = 0.25f
    private val mShrinkDistance = 2.0f

    override fun onLayoutCompleted(state: RecyclerView.State?) {
        super.onLayoutCompleted(state)
        scaleChild()
    }

    override fun scrollHorizontallyBy(dx: Int, recycler: Recycler?, state: RecyclerView.State?): Int {
        val orientation = orientation
        return if (orientation == HORIZONTAL) {
            scaleChild()
            super.scrollHorizontallyBy(dx, recycler, state)
        } else {
            0
        }
    }

    private fun scaleChild() {
        val midPoint = width / 2f-100
        val d1: Float = mShrinkDistance * midPoint
        for (i in 0 until childCount) {
            val child: View? = getChildAt(i)
            val childMidPoint = (child?.let { getDecoratedRight(it) }?.plus(getDecoratedLeft(child)))?.div(2f)
            val d = d1.coerceAtMost(abs(midPoint - childMidPoint!!))
            val scale: Float = 1.05f - mShrinkAmount * d / d1
            child.scaleY = scale
            child.scaleX = scale

            val button = child.findViewById<MaterialButton>(R.id.btnRaiseClaim)
            // Change the background color of the child view
            if (scale >= 1.0f) {
                button.backgroundTintList = ColorStateList.valueOf((ContextCompat.getColor(child.context,R.color.brand_color)))
                button.strokeWidth = 0
                button.setTextColor(ColorStateList.valueOf((ContextCompat.getColor(child.context,R.color.white))))
            } else {
                button.backgroundTintList = ColorStateList.valueOf((ContextCompat.getColor(child.context,R.color.white))) // Set your desired color here
                button.strokeWidth = 2
                button.strokeColor = ColorStateList.valueOf((ContextCompat.getColor(child.context,R.color.brand_color)))
                button.setTextColor(ColorStateList.valueOf((ContextCompat.getColor(child.context,R.color.brand_color))))
            }
        }
    }
}