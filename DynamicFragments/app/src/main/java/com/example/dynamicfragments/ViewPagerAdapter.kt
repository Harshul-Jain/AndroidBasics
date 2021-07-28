package com.example.dynamicfragments

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager2.adapter.FragmentStateAdapter

class ViewPagerAdapter(fm: FragmentManager) : FragmentStatePagerAdapter(fm) {

    private val list = arrayListOf<Fragment>()

    fun add(fragment:Fragment){
        list.add(fragment)
    }
    override fun getCount():Int=list.size

    override fun getItem(position: Int): Fragment {
        return list[position]
    }

}