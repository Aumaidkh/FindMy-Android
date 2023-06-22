package com.hopcape.findmy.core.utils


import android.content.Context
import android.text.InputType
import android.text.TextWatcher
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.widget.doOnTextChanged
import com.google.android.material.textfield.TextInputEditText
import com.hopcape.findmy.R


class FindMyLabeledInputField : LinearLayout {

    private lateinit var labelTv: TextView
    private lateinit var error: TextView
    private lateinit var etInput: TextInputEditText


    private var textWatcher: TextWatcher? = null

    private var showError = false

    constructor(context: Context) : super(context) {
        init(context,null)
    }

    constructor(context: Context, attrs: AttributeSet?) : super(context, attrs) {
        init(context,attrs)
    }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(context, attrs, defStyleAttr) {
        init(context,attrs)
    }

    private fun init(context: Context, attrs: AttributeSet?) {
        val inflater = LayoutInflater.from(context)
        inflater.inflate(R.layout.labelled_input_field, this, true)

        // Initialize the views
        labelTv = findViewById(R.id.labelTv)
        etInput = findViewById(R.id.etInput)
        error = findViewById(R.id.error)
        etInput.doOnTextChanged { text, start, before, count ->  }

        // Retrieve custom attributes
        val typedArray = context.obtainStyledAttributes(attrs, R.styleable.FindMyLabeledInputField)

        val label = typedArray.getString(R.styleable.FindMyLabeledInputField_label)
        labelTv.text = label

        val hint = typedArray.getString(R.styleable.FindMyLabeledInputField_hint)
        etInput.hint = hint

        val inputType = typedArray.getString(R.styleable.FindMyLabeledInputField_input_type)
        setInputType(inputType)

        val value = typedArray.getString(R.styleable.FindMyLabeledInputField_value)
        etInput.setText(value)

        val errorMessage = typedArray.getString(R.styleable.FindMyLabeledInputField_error)
        error.text = errorMessage

        showError = typedArray.getBoolean(R.styleable.FindMyLabeledInputField_showError, false)
        typedArray.recycle()

        // Set initial error visibility
        if (showError) {
            error.visibility = View.VISIBLE
        } else {
            error.visibility = View.GONE
        }
    } // You can add additional methods to manipulate and interact with the views

    fun setOnTextChangeListener(watcher: TextWatcher) {
        textWatcher = watcher
        etInput.removeTextChangedListener(textWatcher)
        etInput.addTextChangedListener(textWatcher)
    }

    var email: String?
        get() = etInput.text.toString()
        set(email) {
            etInput.setText(email)
        }

    fun setEmailError(error: String?) {
        this.error.text = error
    }

    private fun setInputType(type: String?) {
        type?.let {
            when (it) {
                "text" -> etInput.inputType = InputType.TYPE_CLASS_TEXT
                "number" -> etInput.inputType = InputType.TYPE_CLASS_NUMBER
                "phone" -> etInput.inputType = InputType.TYPE_CLASS_PHONE
                "textEmailAddress" -> etInput.inputType = InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS
                "password" -> etInput.inputType = InputType.TYPE_TEXT_VARIATION_PASSWORD
                // Add more cases for other input types as needed
            }
        }
    }
}