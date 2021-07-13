package com.example.dangerouspermissions

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnDial.setOnClickListener {
            val telNo=etPhNo.text.toString()
            val uri= Uri.parse("tel:"+telNo)
            val i=Intent(Intent.ACTION_VIEW,uri)//ACTION_VIEW and ACTION_DIAL will show Dial page
            // but ACTION_CALL will directly CALL
            startActivity(i)
            //the App crashes because of Security Exception
            //so we will add permission in Android Manifest
        }
    }
}