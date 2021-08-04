package com.example.roomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*

class MainActivity : AppCompatActivity() {

    val db by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "User.db"
        )
            .fallbackToDestructiveMigration()
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button.setOnClickListener {
            GlobalScope.launch(Dispatchers.IO) {
                db.userDao().insert(User("Harshul", "9868837145", "Delhi", 20))
            }
        }
        button2.setOnClickListener {
            runBlocking {
                val list: Deferred<List<User>> =
                    GlobalScope.async(Dispatchers.IO) { db.userDao().getAllUser() }
                if (list.await().isNotEmpty()) {
                    with(list.await()[0]) {
                        textView.text = name +id
                        textView2.text = age.toString()
                        textView3.text = address
                        textView4.text = number
                    }
                }
            }
        }
    }

}