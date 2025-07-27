package com.example.tms_android_homework

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.transition.Visibility
import com.example.tms_android_homework.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val myViewModel: MyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myAdapter = MyAdapter(myViewModel)
        binding.list.adapter = myAdapter
        binding.list.layoutManager = LinearLayoutManager(this)

        myViewModel.listState.observe(this, Observer { list ->
            myAdapter.updateList(list)
            if (myAdapter.itemCount > 0) {
                binding.noDataText.visibility = View.GONE
                binding.list.visibility = View.VISIBLE
            } else {
                binding.noDataText.visibility = View.VISIBLE
                binding.list.visibility = View.GONE
            }
        })

        binding.addItem.setOnClickListener {
            val str = binding.newItemText.text.toString()
            if (str != "") {
                //myAdapter.addItem(str)
                myViewModel.addItem(str)
                Snackbar.make(binding.root, "Добавлен новый элемент", Snackbar.LENGTH_SHORT).show()
            } else {
                Snackbar.make(binding.root, "Введите текст в поле для ввода", Snackbar.LENGTH_SHORT).show()
            }

        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}