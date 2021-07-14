package com.example.listviews

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.list_item_fruit.view.*


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        lvFruits.adapter=ArrayAdapter(
            this,
            R.layout.list_item_fruit,
            R.id.tvFruitName,
            arrayOf(
                "Apple",
                "Mangoes",
                "Guavas",
                "Bananas",
                "Kiwis",
                "Grapes",
                "Watermelons",
                "Pineapples",
                "Strawberries",
                "Papayas",
                "Blueberries",
                "Melons"
            )
        )
        
    }
}