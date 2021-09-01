package com.example.workmanager

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.example.workmanager.networking.Client
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GithubWorker(val context: Context, val params: WorkerParameters) :
    CoroutineWorker(context, params) {
    override suspend fun doWork(): Result {
        val response = withContext(Dispatchers.IO) {
            Client.api.getUsers()
        }
        return if (response.isSuccessful) {
            Log.i("Worker Request", "Work Completed")
            Result.success()
        } else {
            Log.i("Worker Request", "Work Restarted")
            Result.retry()
        }
    }

}