package com.example.jobscheduler

import android.app.job.JobInfo
import android.app.job.JobScheduler
import android.content.ComponentName
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {
    val JOB_ID = 123

    //Excellent Article
    //https://medium.com/google-developers/scheduling-jobs-like-a-pro-with-jobscheduler-286ef8510129
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun scheduleJob(view: View) {
        var jobScheduler: JobScheduler = getSystemService(Context.JOB_SCHEDULER_SERVICE) as JobScheduler;
        jobScheduler.schedule(JobInfo.Builder(JOB_ID,
                ComponentName(this, DemoJob::class.java))
                .setPeriodic(1200000,600000)
                .setRequiredNetworkType(JobInfo.NETWORK_TYPE_ANY)
                .setRequiresCharging(true)
                .build()
        )
    }
}