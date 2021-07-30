package com.example.alarms

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Intent
import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.button)
        button.setOnClickListener {
            val i: Intent = Intent(baseContext, MainActivity2::class.java)
            //Schedule a task that will run 5 minutes from now and start my app
            val pi = PendingIntent.getActivity(baseContext, 123, i, PendingIntent.FLAG_ONE_SHOT)
            val alarmManager: AlarmManager = getSystemService(ALARM_SERVICE) as AlarmManager
            //alarmManager.set(AlarmManager.ELAPSED_REALTIME, SystemClock.elapsedRealtime()+1000, pi)
            // current time + the time delay for scheduling the alarm
            alarmManager.setRepeating(
                AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + 100,
                100,
                pi
            )
            //Since Android lollipop Job Scheduler have been Introduced
        }
    }
}