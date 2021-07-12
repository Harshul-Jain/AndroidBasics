package com.example.permissions

import android.content.Context
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkInfo
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnStatusBar.setOnClickListener {
            val cm :ConnectivityManager?= getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val netInfo:Network?= cm?.activeNetwork
            val isConnected=netInfo != null
            tvStatus.setText(if(isConnected)"CONNECTED" else "DISCONNECTED")

        }
    }
}