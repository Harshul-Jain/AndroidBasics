package com.example.jobscheduler

import android.app.job.JobParameters
import android.app.job.JobService

class DemoJob : JobService() {
    override fun onStartJob(params: JobParameters?): Boolean {
        return false
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        return false
    }

}