package com.example.tms_android_homework.subtask1

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.tms_android_homework.R
import com.example.tms_android_homework.subtask3.ApiClient
import com.example.tms_android_homework.subtask3.ApiManager

class MainActivity : AppCompatActivity() {
    private lateinit var fetchData: FetchData

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        fetchData = FetchData(UserManager(), ApiManager(ApiClient()))

        val userName = "John"
        val textView: TextView = findViewById(R.id.textView)
        val button: Button = findViewById(R.id.button)
        button.setOnClickListener {
            val data = fetchData.fetch(userName)
            textView.text = data
        }
    }
}