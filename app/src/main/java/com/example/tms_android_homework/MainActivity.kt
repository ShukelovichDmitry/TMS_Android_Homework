package com.example.tms_android_homework

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tms_android_homework.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val myAdapter = MyAdapter(this)
        binding.pager.adapter = myAdapter
        binding.addFragment.setOnClickListener {
            if (myAdapter.addFragment()) {
                binding.pager.setCurrentItem(myAdapter.itemCount - 1, false)
                binding.tabs.addTab(binding.tabs.newTab().apply { text = "Tab #${myAdapter.itemCount - 1}" })
                Snackbar.make(binding.root, "Добавлен новый фрагмент", Snackbar.LENGTH_SHORT).show()
            }
        }
        binding.cancel.setOnClickListener {
            if (myAdapter.deleteLastFragment()) {
                if (myAdapter.itemCount == binding.pager.currentItem)
                    binding.pager.setCurrentItem(myAdapter.itemCount - 1, false)
                binding.tabs.removeTabAt(myAdapter.itemCount)
                Snackbar.make(binding.root, "Последнее действие отменено ", Snackbar.LENGTH_SHORT).show()
            }

        }

        TabLayoutMediator(binding.tabs, binding.pager) { tab, position ->
            tab.text = "Tab #$position"
        }.attach()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}