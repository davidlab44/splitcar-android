package com.globant.splitcar.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.globant.splitcar.R

class JoinRouteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_join_route)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Unirse a la Ruta"

    }
}
