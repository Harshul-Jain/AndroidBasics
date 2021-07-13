package com.example.dangerouspermissions

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*
import java.util.jar.Manifest

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnDial.setOnClickListener {
            //Check if we have permission
            val perm =
                ContextCompat.checkSelfPermission(this, android.Manifest.permission.CALL_PHONE)
            if (perm == PackageManager.PERMISSION_GRANTED) {
                callNumber()
            } else {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf<String>(
                        android.Manifest.permission.CALL_PHONE
                    ),
                    121
                )
            }
        }
    }

    private fun callNumber() {
        val telNo = etPhNo.text.toString()
        val uri = Uri.parse("tel:$telNo")
        val i = Intent(Intent.ACTION_CALL, uri)
        startActivity(i)
    }
}