package com.example.mvvm.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mvvm.R
import com.example.mvvm.data.models.User
import com.example.mvvm.ui.adapter.UsersAdapter
import com.example.mvvm.ui.viewmodel.GithubViewModel
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_user.*

class MainActivity : AppCompatActivity() {
    val vm by lazy {
        ViewModelProvider(this).get(GithubViewModel::class.java)
    }
    val list = arrayListOf<User>()
    val adapter = UsersAdapter(list)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        usersRv.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = this@MainActivity.adapter
        }
        vm.fetchUsers()
        vm.users.observe(this, Observer {
            if (!list.isNullOrEmpty()) {
                list.addAll(it)
                adapter.notifyDataSetChanged()
            }
        })
    }
}