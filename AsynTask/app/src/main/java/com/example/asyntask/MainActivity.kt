package com.example.asyntask

import android.content.ContentValues.TAG
import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnStart.setOnClickListener {
            val tvCounter=findViewById<TextView>(R.id.tvCounter)
            CountTask().execute(5)
        }
    }

}

class CountTask() : AsyncTask<Int, Int, Unit>() {
    private val tag: String? = "Async"
    override fun doInBackground(vararg params: Int?) {
        Log.d(tag, "doInBackground: started")
        val n: Int = params[0]!!
        for (i in 0 until n) {
            wait1Sec()
            publishProgress(i)
        }
    }
    private fun wait1Sec():Unit{
        val start =System.currentTimeMillis()
        while(System.currentTimeMillis()<start+1000){}
    }

    override fun onProgressUpdate(vararg values: Int?) {
        super.onProgressUpdate(*values)
        //WANT TO WRITE TEXT IN TEXT VIEW BUT HOW TO GET VIEW HERE
    }

}
