package com.developers.koin.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.developers.koin.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity(), MainView {


    private val presenter by inject<MainPresenter>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.attachView(this)
        hello_button.setOnClickListener {
            presenter.showMessage()
        }
    }

    override fun showError(error: String) {
        //Show Error here
    }

    override fun showMessage(hello: String) {
        textView.visibility = View.VISIBLE
        textView.text = hello
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.detachView()
    }
}
