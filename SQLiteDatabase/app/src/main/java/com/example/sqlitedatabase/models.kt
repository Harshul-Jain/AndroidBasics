package com.example.sqlitedatabase

import android.os.AsyncTask

data class Todo(var task: String,var done:Boolean){
    override fun toString(): String {
        return this.task
    }
}