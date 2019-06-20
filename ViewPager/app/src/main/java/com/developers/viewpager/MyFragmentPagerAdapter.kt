package com.developers.viewpager

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * Created by Amanjeet Singh on 16/11/17.
 */
class MyFragmentPagerAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {


    override fun getItem(position: Int): Fragment {
        if (position == 0) {
            return Page1()
        } else{
            return Page2()
        }
    }

    override fun getCount(): Int {
        return 2
    }

}