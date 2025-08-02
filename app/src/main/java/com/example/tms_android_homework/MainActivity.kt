package com.example.tms_android_homework

import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        firstSubTask()

        secondSubTask()
    }

    private fun firstSubTask() {
        val textView1 = findViewById<TextView>(R.id.textView1)
        initTextViewSettings(textView1)
        val textView2 = findViewById<TextView>(R.id.textView2)
        initTextViewSettings(textView2)
    }

    private fun initTextViewSettings(textView: TextView) {
        textView.text = "Hello, user!"
        textView.setTextColor(Color.BLACK)
        textView.textSize = 18f
    }

    private fun secondSubTask() {
        val textView = findViewById<TextView>(R.id.textView)

//        val message = listOf("Hello").map {
//            if (it == "Hello") {
//                "Hello, user!"
//            } else {
//                "Welcome"
//            }
//        }.first()

        textView.text = "Hello, user!"

    }
}
