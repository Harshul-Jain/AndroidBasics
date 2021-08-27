package com.example.firebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.EditText
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_main.*

/*Topic - Firebase:- A very popular and common topic you as a Android developer need to know about .
Firebase is google solution to building app that do not require a server because
as a Android dev we generally focuses on Client part of it  that is the App and we don't want
to establish a  server ,get worried about server cost ,server runtime and all that things.
So firebase is solution to all that problem . Using firebase you can use all the cloud services
in your app without creating a server that can handle all that things.
*/
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbRef: DatabaseReference = FirebaseDatabase.getInstance().reference
        var notes: ArrayList<String> = ArrayList()
        val arrayAdapter: ArrayAdapter<String> = ArrayAdapter(
            this,
            R.layout.item_row,
            R.id.tViewList,
            notes
        )

        listView.adapter = arrayAdapter

        btnDb.setOnClickListener {
            val note: String = etNote.text.toString()
            //Upload the note to Firebase
            // FirebaseDatabase.getInstance().reference.setValue(note)
            // the above line override the previous value So to make it
            // separate we write
            // FirebaseDatabase.getInstance().reference.push().setValue(note)
            // to insert to particular node we write a DatabaseReference
            dbRef.child("note").push().setValue(note)
            //dbRef.child("todo").push().setValue(note)
        }
        /*Let's see how reading data from database actually works
        Firebase is termed as a Realtime Database.what does 'Realtime' keyword means?
        This means whenever the data in your database is updated,all the connecting devices
        get a call back  with the updated data.As soon as you update data in your
        database , you do not have to fetch the data again, you automatically get Callback in
        your device(here is the updated data,Take it and update to your client).That part we have
        to implement . So that is the API that firebase provides us .Now how to use this api.
        To use , we have to add Listener to particular node.The Listener can be addValueEventListener
        or addChildEventListener in which addChildEventListener is preferred.Why? Let's see
        */
        dbRef.child("note").addChildEventListener(object : ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                // Called when a new data node is inserted to the "note" node
                val data = snapshot.value
                notes.add(data.toString())
                arrayAdapter.notifyDataSetChanged()
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                // An Existing data node is updated
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                //when a data at a subNode is removed
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                //when the position of a subNode changes
            }

            override fun onCancelled(error: DatabaseError) {
                //when the read operation failed
            }

        })
        /* The Second Listener is addValueEventListener. What addValueEventListener does is that
           every time there is a data update it will give you entire database again instead of
           giving latest child that have been updated . This is helpful in some situation but in most of
           the cases we handle this is not very needed as it unnecessarily increase the data
           payload that get from the server and increase the response time,data usage of your
           user .So addValueEventListener is not a good idea.
        */
        dbRef.child("note").addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                //Gets the entire database regardless of the operation
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
}