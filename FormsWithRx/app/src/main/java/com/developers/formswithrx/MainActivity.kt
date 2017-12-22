package com.developers.formswithrx

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.jakewharton.rxbinding2.widget.textChangeEvents
import io.reactivex.Observable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.BiFunction
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {

    var conditionMet: Boolean = false
    lateinit var d: Disposable

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onStart() {
        super.onStart()

        val usernameObservable = username_editText.textChangeEvents()
                .map({ textViewTextChangeEvent ->
                    textViewTextChangeEvent.text().toString()
                })
        val passwordObservable = password_editText.textChangeEvents()
                .map({ textViewTextChangeEvent ->
                    textViewTextChangeEvent.text().toString()
                })

        d = Observable.zip(usernameObservable, passwordObservable
                , object : BiFunction<String, String, Boolean> {
            override fun apply(username: String, password: String): Boolean {
                if (conditionForUserName(username) && conditionForPassword(password)) {
                    conditionMet = true
                    return conditionMet
                } else {
                    conditionMet=false
                    return conditionMet
                }
            }
        }).subscribe({
            if (conditionMet) {
                runOnUiThread {
                    login_button.isEnabled = true
                }
            } else {
                runOnUiThread {
                    login_button.isEnabled = false
                }
            }
        }, { e -> e.printStackTrace() })

        login_button.setOnClickListener({
            toast("Process your Login")
        })
    }

    fun Context.toast(msg: CharSequence) {
        Toast.makeText(applicationContext, msg, Toast.LENGTH_SHORT).show()
    }

    //Set conditions for username
    fun conditionForUserName(username: String): Boolean {
        if (username.length >= 2) {
            return true
        }
        return false
    }

    //Set Conditions for Password
    fun conditionForPassword(password: String): Boolean {
        if (password.length >= 2) {
            return true
        }
        return false
    }

    override fun onStop() {
        super.onStop()
        if (!(d.isDisposed)) {
            d.dispose()
        }

    }
}
