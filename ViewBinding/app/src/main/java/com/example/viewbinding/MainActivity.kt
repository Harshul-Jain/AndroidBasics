package com.example.viewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        /*
        one way
        textView.text="10"
        textView.SetTextColor(getColor(R.color.colorAccent))
        textView.textSize=30f
        //how to convert f into sp and dp
         */

        /* Second way
    with(textView){
        text="10"
        SetTextColor(getColor(R.color.colorAccent))
        textSize=30f
    }
    */
        /*3rd way-preferred
    textView.apply{
        text="10"
        SetTextColor(getColor(R.color.colorAccent))
        textSize=30f
    }
     */
        editText.apply{
            //isEnabled=false
            setText("Harshul Jain")
            addTextChangedListener {
            Log.i("View Binding",it.toString())
            if(it.toString().length>6 && it.toString().length<20){
                button.isEnabled=true
            }
                else{
                button.isEnabled=false
            }
                editText.requestFocus()
            }
        }
        /*first method for button
        button.setOnClickListener(View.OnClickListener{
            override fun onClick(v:View){
                Toast.makeText(v.context,"Button pressed from annoymous function",Toast.LENGTH_LONG).show()
            }
        })
         */
        /*2nd  way
        button.setOnclickListener(this)
        here we will have implement interface onClickListener above at MainActivity
        and implement required function  onClick outside onCreate()
         */
        //3rd method-preferred
        button.setOnClickListener {
            Toast.makeText(it.context,"Button pressed",Toast.LENGTH_LONG).show()
        }
        checkBox.setOnCheckedChangeListener { buttonView, isChecked ->
            Toast.makeText(buttonView.context,"This is a check box",Toast.LENGTH_SHORT).show()
        }
        radioGroup.setOnCheckedChangeListener {group, checkedId ->
            when(checkedId){

                R.id.radioButton->{
                    Toast.makeText(group.context,"This is a radio Button 1",Toast.LENGTH_SHORT).show()
                }
                R.id.radioButton2->{
                    Toast.makeText(group.context,"This is a fadio Button 2",Toast.LENGTH_SHORT).show()
                }
            }
        }

    }

    override fun onClick(v: View) {
        Toast.makeText(v.context,"Button pressed from interface function",Toast.LENGTH_LONG).show()
    }
    

}