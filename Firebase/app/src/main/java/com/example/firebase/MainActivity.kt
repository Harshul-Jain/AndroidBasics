package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import kotlinx.android.synthetic.main.activity_main.*

/*Topic - Firebase:- A very popular and common topic you as a Android developer need to know about .
Firebase is google solution to building app that do not require a server because
as a Android dev we generally focuses on Client part of it  that is the App and we don't want
to establish a  server ,get worried about server cost ,server runtime and all that things.
So firebase is solution to all that problem . Using firebase you can use all the cloud services
in your app without creating a server that can handle all that things.
*/
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnDb.setOnClickListener {
            val note:String = etNote.text.toString()
            //Upload the note to Firebase
            

        }
    }
}