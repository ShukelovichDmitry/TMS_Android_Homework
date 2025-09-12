package com.example.tms_android_homework.background.presentation

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.widget.Toast
import com.example.tms_android_homework.R

class PowerConnectionReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        if (intent?.action == Intent.ACTION_POWER_CONNECTED) {
            Toast.makeText(context, R.string.power_connected, Toast.LENGTH_LONG).show()
            context?.startForegroundService(Intent(context, ChargingService::class.java))
        } else {
            Toast.makeText(context, R.string.power_disconnected, Toast.LENGTH_LONG).show()
            context?.stopService(Intent(context, ChargingService::class.java))
        }
    }
}