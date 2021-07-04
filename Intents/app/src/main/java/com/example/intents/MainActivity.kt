package com.example.intents

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

const val KEY_1="Name"
const val KEY_2="Age"
const val KEY_3="Student"
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Explicit Intent
        btn.setOnClickListener{
            val i=Intent(this,MainActivity2::class.java)
            i.putExtra(KEY_1,"HARSHUL")
            i.putExtra(KEY_2,18)
            i.putExtra(KEY_3,true)
            startActivity(i)
        }
        //Implicit Intent
        mailBtn.setOnClickListener{
            val email=editText.text.toString()
            val i=Intent()
            i.action=Intent.ACTION_SENDTO
            i.data= Uri.parse("mailto:$email")
            startActivity(i)
        }
        webBtn.setOnClickListener{
            val website=editText.text.toString()
            val i=Intent()
            i.action=Intent.ACTION_VIEW
            i.data= Uri.parse("https://$website")
            startActivity(i)
        }
        telBtn.setOnClickListener{
            val number=editText.text.toString()
            val i=Intent()
            i.action=Intent.ACTION_DIAL
            i.data= Uri.parse("tel:$number")
            startActivity(i)
        }
    }
}