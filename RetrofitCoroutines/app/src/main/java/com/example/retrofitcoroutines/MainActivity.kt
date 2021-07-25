package com.example.retrofitcoroutines

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.lang.Exception


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val r = Retrofit.Builder()
            .baseUrl("https://reqres.in/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val api = r.create(ReqResAPI::class.java)

        GlobalScope.launch(Dispatchers.IO) {
            try {
                val response = api.getUsers()
                //in Dispatchers.IO Toast does not works

                GlobalScope.launch(Dispatchers.Main) {
                    Toast.makeText(this@MainActivity, "Request Success", Toast.LENGTH_SHORT).show()
                    response.body()?.users?.let { printUsers(it) }
                    //function containing adapter should be kept on main ie Dispatchers.MAIN ,in this case printUsers(it)

                }


            } catch (e: Exception) {
                Toast.makeText(this@MainActivity, "Request Failed", Toast.LENGTH_SHORT).show()

            }
        }

    }

    private fun printUsers(users: List<UsersResponse.User>) {
        lvPeople.adapter = ArrayAdapter<String>(
            this,
            android.R.layout.simple_list_item_1,
            android.R.id.text1,
            users.map { it.firstName }
        )
    }
}