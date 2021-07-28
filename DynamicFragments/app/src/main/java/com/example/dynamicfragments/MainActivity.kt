package com.example.dynamicfragments

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container,FirstFragment())
            .commitNow()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.container,BlankFragment())
            .commit()
    }
}