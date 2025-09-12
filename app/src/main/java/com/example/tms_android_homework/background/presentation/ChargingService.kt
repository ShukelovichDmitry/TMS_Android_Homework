package com.example.tms_android_homework.background.presentation

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.core.app.NotificationCompat
import com.example.tms_android_homework.R
import com.example.tms_android_homework.note.presentation.MainActivity

class ChargingService: Service() {

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        setNotification()
        return super.onStartCommand(intent, flags, startId)
    }

    private fun setNotification() {
        val channelId =
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                createNotificationChannel()
            } else {
                ""
            }

        val intent = Intent(this, MainActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        }

        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
        val notification = notificationBuilder.setOngoing(true)
            .setSmallIcon(R.drawable.outline_edit_24)
            .setContentTitle(resources.getString(R.string.notification_title))
            .setContentText(resources.getString(R.string.notification_text))
            .setPriority(Notification.PRIORITY_MIN)
            .setCategory(Notification.CATEGORY_SERVICE)
            .setContentIntent(pendingIntent)
            .build()
        startForeground(1, notification)
    }

    private fun createNotificationChannel(): String {
        val channelId = "charging_service"
        val channelName = "Charging Service"
        val chan = NotificationChannel(
            channelId,
            channelName,
            NotificationManager.IMPORTANCE_HIGH
        )
        chan.importance = NotificationManager.IMPORTANCE_NONE
        chan.lockscreenVisibility = Notification.VISIBILITY_PUBLIC
        val service =
            getSystemService(
                Context.NOTIFICATION_SERVICE
            ) as NotificationManager
        service.createNotificationChannel(chan)
        return channelId
    }
}