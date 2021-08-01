package com.example.sqlitedatabase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import com.example.sqlitedatabase.db.MyDbHelper
import com.example.sqlitedatabase.db.TodoTable
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private val todos = ArrayList<Todo>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val todoAdapter = ArrayAdapter<Todo>(
            this,
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            todos
        )
        val db = MyDbHelper(this).writableDatabase
        fun refreshTodoList() {
            val todoList = TodoTable.getAllTodos(db)
            todos.clear()
            todos.addAll(todoList)
            todoAdapter.notifyDataSetChanged()
        }
        refreshTodoList()
        lvTodo.adapter = todoAdapter
        btnAddTodo.setOnClickListener {
            val newTodo = Todo(
                etNewTodo.text.toString(),
                false
            )
            TodoTable.insertTodo(db, newTodo)
            refreshTodoList()
        }
    }

}