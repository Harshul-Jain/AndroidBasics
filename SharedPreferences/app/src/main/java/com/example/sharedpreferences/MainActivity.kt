package com.example.sharedpreferences

import android.content.Context
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*



class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var sPref = getSharedPreferences("COLOR", Context.MODE_PRIVATE)
        var color = sPref.getInt("COLOR", Color.WHITE)
        llBackground.setBackgroundColor(color)

        fun savedColor(color: Int) {
            var editor = sPref.edit()
            editor.putInt("COLOR", color)
            editor.apply()
        }
        btnRed.setOnClickListener {
            llBackground.setBackgroundColor(Color.RED)
            savedColor(Color.RED)
        }
        btnBlue.setOnClickListener {
            llBackground.setBackgroundColor(Color.BLUE)
            savedColor(Color.BLUE)
        }
        btnGreen.setOnClickListener {
            llBackground.setBackgroundColor(Color.GREEN)
            savedColor(Color.GREEN)
        }
    }
}