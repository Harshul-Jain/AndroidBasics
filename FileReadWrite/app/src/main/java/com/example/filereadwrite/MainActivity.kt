package com.example.filereadwrite

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat
import java.io.File
import java.nio.charset.Charset
import java.nio.file.StandardOpenOption

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val textView: TextView = findViewById(R.id.textView)
        val editText: EditText = findViewById(R.id.editText)
        val btnWrite: Button = findViewById(R.id.btnWrite)
        val btnRead: Button = findViewById(R.id.btnRead)
        btnWrite.setOnClickListener {
            val dataDir = ContextCompat.getDataDir(this)
            val myFile = File(dataDir, "file.txt")
            // myFile.writeText(editText.text.toString())
            /*writeText() function creates a new file if it does not exist already and writes the text (string argument)
            to the file. If an empty string is provided, the file is created and nothing is written to it.
            if file exists in the path already then the file will be overridden, Sets the content of this file as
            text encoded using UTF-8 or charset specified by the user.
             */
            myFile.appendText(editText.text.toString())
            //You can append text into an existing file in Kotlin by opening a file using
            // appendText() function in File class
        }
        btnRead.setOnClickListener {
            val dataDir = ContextCompat.getDataDir(this)
            val myFile = File(dataDir, "file.txt")
            textView.text = myFile.readText()
        }
    }
}