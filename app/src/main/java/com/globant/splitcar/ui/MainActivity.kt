package com.globant.splitcar.ui

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.globant.splitcar.R
import com.globant.splitcar.adapters.RoutesAdapter
import com.globant.splitcar.model.addAllRoutes
import com.globant.splitcar.model.showAllRoutes
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
        }
    }

    override fun onPause() {
        super.onPause()
        recyclerViewRoutes.layoutManager = LinearLayoutManager(this)
        recyclerViewRoutes.adapter = RoutesAdapter(showAllRoutes(), this)
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
