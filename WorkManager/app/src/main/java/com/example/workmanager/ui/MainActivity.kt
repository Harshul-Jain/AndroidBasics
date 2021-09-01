package com.example.workmanager.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.work.*
import com.example.workmanager.GithubWorker
import com.example.workmanager.R
import kotlinx.android.synthetic.main.activity_main.*
import java.util.concurrent.TimeUnit

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        workerBtn.setOnClickListener {
            setupGithubWorker()
        }
    }

    private fun setupGithubWorker() {

        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()
//        val worker = OneTimeWorkRequestBuilder<GithubWorker>()
//            .setInitialDelay(30,TimeUnit.SECONDS)
//            .setConstraints(constraints)
//            .build()
        val worker = PeriodicWorkRequestBuilder<GithubWorker>(1, TimeUnit.DAYS)
            .setInitialDelay(8, TimeUnit.HOURS)
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(this).enqueue(worker)
    }
}