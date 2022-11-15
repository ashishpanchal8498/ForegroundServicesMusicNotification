package com.example.foreground

import android.app.ActivityManager
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.example.foreground.databinding.ActivityMainBinding

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)
        binding.button.setOnClickListener{
          this.startStopService()
        }
    }

    private fun startStopService() {
        if (isMyServiceRunning(MainActivity::class.java)) {

            Toast.makeText(this,
                "Service Stopped",
                Toast.LENGTH_SHORT
            ).show()

            stopForService()
        }else{

            Toast.makeText(this,
                "Service Started",
                Toast.LENGTH_SHORT
            ).show()

            startForegroundService(Intent(this,
                Foreground::class.java))

        }
    }

    private fun stopForService() {

    }

    private fun isMyServiceRunning(mClass: Class<MainActivity>): Boolean {

        val manager: ActivityManager = getSystemService(
            Context.ACTIVITY_SERVICE
        ) as ActivityManager

        for (service: ActivityManager.RunningServiceInfo in
        manager.getRunningServices(Integer.MAX_VALUE))
            if (mClass.name.equals(service.service.className)){
                return true
            }
        return false
    }
}
