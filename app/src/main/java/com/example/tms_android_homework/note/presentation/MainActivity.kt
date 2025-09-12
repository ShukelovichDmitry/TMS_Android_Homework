package com.example.tms_android_homework.note.presentation

import android.content.Intent
import android.content.IntentFilter
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tms_android_homework.R
import com.example.tms_android_homework.background.presentation.PowerConnectionReceiver

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val powerConnectionReceiver = PowerConnectionReceiver()
        IntentFilter(Intent.ACTION_POWER_CONNECTED).also {
            this.registerReceiver(powerConnectionReceiver, it, RECEIVER_EXPORTED)
        }
        IntentFilter(Intent.ACTION_POWER_DISCONNECTED).also {
            this.registerReceiver(powerConnectionReceiver, it, RECEIVER_EXPORTED)
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ListFragment())
            .commit()

    }
}