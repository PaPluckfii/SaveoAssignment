package com.sumeet.saveoassignment.view.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import com.sumeet.saveoassignment.R
import com.sumeet.saveoassignment.view.home.HomeScreenActivity

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        Handler().postDelayed(Runnable {
            val intent = Intent(this, HomeScreenActivity::class.java)
            startActivity(intent)
        }, 2000)

    }

    override fun onStop() {
        super.onStop()
        finish()
    }
}