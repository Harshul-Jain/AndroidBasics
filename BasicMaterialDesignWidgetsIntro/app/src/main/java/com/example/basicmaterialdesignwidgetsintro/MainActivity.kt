package com.example.basicmaterialdesignwidgetsintro

import android.content.DialogInterface
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.textclassifier.TextClassifier
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))

        val alertDialog: AlertDialog = AlertDialog.Builder(this)
            .setTitle("Hello from AlertDialog")
            .setMessage("I'm a generic alert dialog!")
            .setCancelable(false)
            .setPositiveButton("Ok",DialogInterface.OnClickListener(){ dialogInterface: DialogInterface, i: Int ->
                Toast.makeText(this, "Hello, I'm Toast!", Toast.LENGTH_SHORT).show()
            })
            .create()
        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            alertDialog.show()
//            Snackbar.make(view, "Hello from Basic Activity", Snackbar.LENGTH_LONG)
//                .setAction("Undo", View.OnClickListener {
//                    Toast.makeText(this, "Hello, I'm Toast!", Toast.LENGTH_SHORT).show()
//                })
//                .show()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
}