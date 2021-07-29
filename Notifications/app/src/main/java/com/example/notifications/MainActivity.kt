package com.example.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.core.app.NotificationCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val nm = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        //Only to work for devices greater than OREO
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            nm.createNotificationChannel(
                NotificationChannel(
                    "first",
                    "default",
                    NotificationManager.IMPORTANCE_DEFAULT
                )
            )
        }
        val button: Button = findViewById(R.id.button)
        val button1: Button = findViewById(R.id.button1)
        button.setOnClickListener {
            val simpleNotification = NotificationCompat.Builder(this, "first")
                .setContentTitle("Simple Title")
                .setContentText("This is sample description of the notification")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()
            nm.notify(1, simpleNotification)
        }
        button1.setOnClickListener {
            val i = Intent()
            i.action = Intent.ACTION_VIEW
            i.data = Uri.parse("https://www.google.com")

            val pi = PendingIntent.getActivity(this, 123, i, PendingIntent.FLAG_UPDATE_CURRENT)
            val clickableNotification = NotificationCompat.Builder(this, "first")
                .setContentTitle("Simple Title")
                .setContentIntent(pi)
                .setAutoCancel(true)
                .setContentText("This is sample description of the notification")
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .build()
            nm.notify(2, clickableNotification)
        }
    }
}