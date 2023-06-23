package com.hopcape.findmy.core.utils

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.PorterDuff
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import androidx.core.content.ContextCompat
import com.google.android.material.button.MaterialButton
import com.hopcape.findmy.R

/**
 * This is a custom widget which renders on
 * ui a button with the following attributes that can be set
 * 1. Button Text
 * 2. Button State
 * 3. Background Color
 * 4. Loading State
 * */
class FindMyProgressButton @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

    private val button: MaterialButton
    private val progressBar: ProgressBar

    private var buttonDisabledColorResId: Int = 0
    private var buttonBackgroundColorResId: Int = 0



    init {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.button_with_progress_bar, this, true)

        button = findViewById(R.id.button)
        progressBar = findViewById(R.id.progressBar)

        progressBar.apply {
            indeterminateDrawable.setColorFilter(ContextCompat.getColor(context,R.color.white), PorterDuff.Mode.SRC_IN)
        }

        attrs?.let { applyAttributes(it) }
    }

    private fun applyAttributes(attrs: AttributeSet) {
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.FindMyProgressButton)

        // Set button text
        val buttonText = typedArray.getString(R.styleable.FindMyProgressButton_buttonText)
        button.text = buttonText

        // Set button background color
        buttonBackgroundColorResId = typedArray.getColor(
            R.styleable.FindMyProgressButton_buttonBackgroundColor,
            resources.getColor(R.color.brand_color) // Default color if not specified
        )
        button.backgroundTintList = ColorStateList.valueOf(buttonBackgroundColorResId)

        // Set button disabled color
        buttonDisabledColorResId = typedArray.getColor(
            R.styleable.FindMyProgressButton_buttonDisabledColor,
            resources.getColor(R.color.disabled_button_color) // Default color if not specified
        )


        typedArray.recycle()
    }

    override fun setEnabled(isEnabled: Boolean) {
        button.isEnabled = isEnabled
        button.backgroundTintList = ColorStateList.valueOf(if (!isEnabled) buttonDisabledColorResId else buttonBackgroundColorResId)
    }

    fun progressBarVisible(isVisible: Boolean) {
        button.isEnabled = !isVisible
        progressBar.visibility = if (isVisible) View.VISIBLE else View.GONE
        button.backgroundTintList = ColorStateList.valueOf(if (isVisible) buttonDisabledColorResId else buttonBackgroundColorResId)
    }

    override fun setOnClickListener(listener: OnClickListener?) {
        button.setOnClickListener {
            listener?.onClick(this)
        }
    }
}