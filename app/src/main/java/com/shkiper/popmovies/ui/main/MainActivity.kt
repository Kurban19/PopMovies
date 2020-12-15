package com.shkiper.popmovies.ui.main

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.shkiper.popmovies.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(findViewById(R.id.toolbar))
        Log.d("Tag", "onCreate called in MainActivity")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d("Tag", "onSaveInstanceState Called in MainActivity")
    }
}