package com.example.foreground

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.media.MediaPlayer
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.foreground.Constant.CHANNEL_ID


class Foreground:Service() {

    private lateinit var musicplayer:MediaPlayer

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        super.onCreate()
//        initMusic()
        showNotification()
        createNotificationChannel()
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        return START_STICKY
    }

    private fun showNotification() {
        Intent(this, MainActivity::class.java)
        val channel = NotificationChannel(
            Constant.CHANNEL_ID,
            "My Foreground Service",
            NotificationManager.IMPORTANCE_HIGH
        )
        val notificaton : NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificaton.createNotificationChannel(channel)

        val notification = Notification
            .Builder(this, CHANNEL_ID)
            .setOngoing(true)
            .setContentText("Music Player")
            .setCategory(Notification.CATEGORY_SERVICE)
            .setAutoCancel(true)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()

        startForeground(112, notification)
    }

    private fun createNotificationChannel() {

        NotificationChannel(
            CHANNEL_ID, "My Service Channel",
            NotificationManager.IMPORTANCE_DEFAULT
        )
    }

    private fun initMusic(){
        musicplayer = MediaPlayer.create(this, R.raw.file_example)
        musicplayer.isLooping = true
        musicplayer.setVolume(100F,100F)
    }
}