package com.developers.usingdagger2.main

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.developers.usingdagger2.InitApplication
import com.developers.usingdagger2.R
import com.developers.usingdagger2.di.component.DaggerActivityComponent
import com.developers.usingdagger2.di.module.ActivityModule
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), MainView {

    @Inject
    lateinit var mainPresenter: MainPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val activityComponent = DaggerActivityComponent.builder()
                .applicationComponent(InitApplication.get(this).component())
                .activityModule(ActivityModule(this))
                .build()
        activityComponent.inject(this)
        mainPresenter.setView(this)
        mainPresenter.showData()
    }

    override fun showMessage(message: String) {
        toast(message)
        main_text_view.text = "Hey $message"
    }

    override fun showLoading() {
        //showing Loading for data
    }


    override fun hideLoading() {
        //hiding loading after the data is loaded
    }

    fun Context.toast(message: CharSequence, duration: Int = Toast.LENGTH_LONG) {
        Toast.makeText(this, message, duration).show()
    }
}
