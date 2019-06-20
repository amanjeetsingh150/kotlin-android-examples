package com.developers.spekexample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var emailValidator: EmailValidator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        emailValidator = EmailValidator()
        edit_text_email.addTextChangedListener(emailValidator)
        validate_button.setOnClickListener {
            if (emailValidator.isValid()) {
                toast("Valid Mail", Toast.LENGTH_SHORT)
            } else {
                toast("Access Denied. Invalid Mail!!", Toast.LENGTH_SHORT)
            }
        }

    }
}
