package com.globant.splitcar.ui

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import com.globant.splitcar.R
import com.globant.splitcar.adapters.RouteListAdapter
import com.globant.splitcar.model.Route
import com.globant.splitcar.utils.EMAIL
import com.globant.splitcar.utils.ID_USER
import com.globant.splitcar.viewmodels.RouteViewModel
import kotlinx.android.synthetic.main.activity_main.fabCreateRoute
import kotlinx.android.synthetic.main.content_main.recyclerViewRoutes

class MainActivity : AppCompatActivity(), RouteEvents {

    private lateinit var routeListAdapter: RouteListAdapter
    private lateinit var routeViewModel: RouteViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        routeListAdapter = RouteListAdapter(this)
        recyclerViewRoutes.layoutManager = LinearLayoutManager(this)
        recyclerViewRoutes.adapter = routeListAdapter
        routeViewModel = ViewModelProviders.of(this).get(RouteViewModel::class.java)
        routeViewModel.getAllRoutes().observe(this, Observer {
            routeListAdapter.addAll(it)
        })
        listenFloatingActionButton()
    }

    private fun listenFloatingActionButton() {
        fabCreateRoute.setOnClickListener {
            val email = intent.getStringExtra(EMAIL)
            val intent = CreateRouteActivity.createIntent(this@MainActivity)
            intent.putExtra(EMAIL, email)
            startActivity(intent)
            //startActivity(MainActivityPro.createIntent(this@MainActivity))
        }
    }

    override fun onItemClicked(route: Route) {
        val intent = JoinRouteActivity.createIntent(this@MainActivity)
        intent.putExtra(ID_USER, route.driverName)
        startActivity(intent)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }
//TODO change recyclerview's lottie
//TODO add a progressbar before load MainActivity
}
