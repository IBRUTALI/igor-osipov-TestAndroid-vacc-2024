package com.ighorosipov.testandroid.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.ighorosipov.testandroid.R
import com.ighorosipov.testandroid.utills.di.appComponent

class MainActivity : AppCompatActivity() {
    private var navController: NavController? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        appComponent().inject(this)
        navController = Navigation.findNavController(this, R.id.nav_host)
    }

}