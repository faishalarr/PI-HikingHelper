package com.example.HikingHelperPI.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.example.HikingHelperPI.fragment.FragmentPeralatan
import com.example.HikingHelperPI.fragment.FragmentVideoTips

class ViewPagerAdapter(fragmentManager: FragmentManager) : FragmentStatePagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        var pages: Fragment? = null
        when (position) {
            0 -> pages = FragmentPeralatan()
            1 -> pages = FragmentVideoTips()
        }
        return pages!!
    }

    override fun getCount(): Int {
        return 2
    }

    override fun getPageTitle(position: Int): CharSequence {
        var title = ""
        when (position) {
            0 -> title = "Peralatan"
            1 -> title = "Tips"
        }
        return title
    }
}