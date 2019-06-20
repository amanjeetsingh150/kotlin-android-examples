package com.developers.kotlintest

import android.text.Editable
import android.text.TextWatcher
import java.util.regex.Pattern

/**
 * Created by Amanjeet Singh on 9/2/18.
 */
class EmailValidator : TextWatcher {

    companion object {
        private val EMAIL_PATTERN = Pattern.compile(
                "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                        "\\@" +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                        "(" +
                        "\\." +
                        "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                        ")+"
        )
    }

    private var isValid = false

    fun isValidEmail(email: CharSequence?): Boolean {
        return email != null && EMAIL_PATTERN.matcher(email).matches()
    }


    override fun afterTextChanged(editable: Editable?) {
        isValid = isValidEmail(editable)
    }

    override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

    }

    override fun onTextChanged(p0: CharSequence?, start: Int, count: Int, after: Int) {

    }

}