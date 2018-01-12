package com.developers.flinganimations

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.animation.DynamicAnimation
import android.support.animation.FlingAnimation
import android.support.design.widget.TabLayout
import kotlinx.android.synthetic.main.bottom_sheet.*
import kotlinx.android.synthetic.main.content_main.*
import android.support.design.widget.BottomSheetBehavior
import android.view.MotionEvent
import android.view.View
import android.view.GestureDetector
import kotlinx.android.synthetic.main.activity_main.*
import java.util.logging.Logger


class MainActivity : AppCompatActivity() {

    var sheetBehavior: BottomSheetBehavior<*>? = null

    companion object {
        val log = Logger.getLogger(MainActivity::class.java.name)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        toolbar.setTitleTextColor(Color.WHITE)
        sheetBehavior = BottomSheetBehavior.from(bottom_sheet_layout)
        sheetBehavior?.setBottomSheetCallback(object : BottomSheetBehavior.BottomSheetCallback() {


            override fun onSlide(bottomSheet: View, slideOffset: Float) {
                log.info("On Slide")
            }

            override fun onStateChanged(bottomSheet: View, newState: Int) {
                log.info("On State changes")
            }

        })
        tab_layout.addTab(tab_layout.newTab()
                .setIcon(R.drawable.ic_location_on_grey_600_24dp))
        tab_layout.addTab(tab_layout.newTab()
                .setIcon(R.drawable.ic_directions_car_grey_700_24dp))
        tab_layout.addTab(tab_layout.newTab().
                setIcon(R.drawable.ic_directions_bus_grey_700_24dp))
        val adapter = ViewPagerAdapter(supportFragmentManager)
        view_pager.adapter = adapter
        view_pager.addOnPageChangeListener(TabLayout
                .TabLayoutOnPageChangeListener(tab_layout))


    }

}
