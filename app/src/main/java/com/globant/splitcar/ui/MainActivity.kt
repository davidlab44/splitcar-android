package com.globant.splitcar.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.globant.splitcar.R
import com.globant.splitcar.adapters.RoutesAdapter
import com.globant.splitcar.model.addAllRoutes
import com.globant.splitcar.model.showAllRoutes
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import kotlinx.android.synthetic.main.activity_main.fabMakeRoute
import kotlinx.android.synthetic.main.content_main.recyclerViewRoutes

class MainActivity : AppCompatActivity() {
    companion object {
        private const val TAG = "KotlinActivity"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recyclerViewRoutes.layoutManager = LinearLayoutManager(this)
        recyclerViewRoutes.adapter = RoutesAdapter(addAllRoutes(), this)
        fabMakeRoute.setOnClickListener {
            val userName = intent.getStringExtra("userName")
            val intent: Intent = RouteActivity.createIntent(this@MainActivity)
            intent.putExtra("userName", userName)
            startActivity(intent)
            basicReadWrite()
        }
    }

    override fun onPause() {
        super.onPause()
        recyclerViewRoutes.layoutManager = LinearLayoutManager(this)
        recyclerViewRoutes.adapter = RoutesAdapter(showAllRoutes(), this)
    }

    private fun basicReadWrite() {
        // [START write_message]
        // Write a message to the database
        val database = FirebaseDatabase.getInstance().reference
        val myRef = database.child("message")
        myRef.setValue("Hello, World!").addOnSuccessListener {
            Log.d(TAG, "Value is: $myRef")
        }.addOnFailureListener {
            Log.d(TAG, "Value is: $myRef")
        }

        // [END write_message]
        // [START read_message]
        // Read from the database
        myRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(dataSnapshot: DataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                val value = dataSnapshot.getValue(String::class.java)
                Log.d(TAG, "Value is: $value")
            }

            override fun onCancelled(error: DatabaseError) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException())
            }
        })
        // [END read_message]
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
