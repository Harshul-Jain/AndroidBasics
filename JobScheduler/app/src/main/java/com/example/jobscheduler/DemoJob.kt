package com.example.jobscheduler

import android.app.job.JobParameters
import android.app.job.JobService
import android.widget.Toast

class DemoJob : JobService() {
    override fun onStartJob(params: JobParameters?): Boolean {
        Toast.makeText(this, "Hello , I'm a scheduled Job", Toast.LENGTH_SHORT).show()
        return false
        /*
        Async job

        return true // This true means that there is work still going on, so don't
        finish this Service just yet
         */
    }



    override fun onStopJob(params: JobParameters?): Boolean {
        return false
    }

}