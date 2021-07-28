package com.example.dynamicfragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bundle = Bundle()
        bundle.putString("KEY", "HARSHUL")
        val fragment = FirstFragment()
        fragment.arguments = bundle

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container, fragment)
            .commitNow()
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