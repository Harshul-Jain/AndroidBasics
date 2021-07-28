package com.example.dynamicfragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager.widget.ViewPager

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bundle = Bundle()
        bundle.putString("KEY", "HARSHUL")
        val fragment = FirstFragment()
        fragment.arguments = bundle

        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager)
        viewPagerAdapter.apply {
            add(fragment)
            add(BlankFragment())
            add(FirstFragment())
        }
        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        viewPager.adapter = viewPagerAdapter
//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.container, fragment)
//            .commitNow()
//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.container, BlankFragment())
//            .commitNow()
//        supportFragmentManager
//            .beginTransaction()
//            .replace(R.id.container,FirstFragment())
//            .commitNow()
    }
}