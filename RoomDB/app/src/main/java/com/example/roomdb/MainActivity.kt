package com.example.roomdb

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.room.Room
import androidx.room.RoomDatabase

class MainActivity : AppCompatActivity() {

    val db by lazy {
        Room.databaseBuilder(
            this,
            AppDatabase::class.java,
            "User.db"
        ).allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        
    }

}