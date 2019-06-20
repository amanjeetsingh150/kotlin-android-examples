package com.developers.viewmodelandroidarch

import androidx.lifecycle.ViewModelProviders
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.developers.viewmodelandroidarch.model.BeyBlade
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var beyBlade: BeyBlade

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        beyBlade = ViewModelProviders.of(this).get(BeyBlade::class.java)
        if (beyBlade.bitBeast != null || beyBlade.beyBladeOwner != null) {
            displayInfo(beyBlade)
        }
        show_button.setOnClickListener {
            beyBlade.beyBladeOwner = owner_name_edittext.text.toString()
            beyBlade.bitBeast = bitbeast_name_edittext.text.toString()
            displayInfo(beyBlade)
        }
    }

    private fun displayInfo(beyBlade: BeyBlade) {
        beyblade_text_view.text = beyBlade.bitBeast + " of " + beyBlade.beyBladeOwner
    }
}
