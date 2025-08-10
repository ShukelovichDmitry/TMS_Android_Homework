package com.example.tms_android_homework.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tms_android_homework.R
import com.example.tms_android_homework.data.DataSourceImpl
import com.example.tms_android_homework.data.ItemRepositoryImpl
import com.example.tms_android_homework.databinding.ActivityMainBinding
import com.example.tms_android_homework.domain.usecase.AddItemUseCase
import com.example.tms_android_homework.domain.usecase.GetItemUseCase
import com.example.tms_android_homework.domain.usecase.GetItemsUseCase
import com.example.tms_android_homework.presentation.listeners.ItemClickListener
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))
        setContentView(binding.root)

        val itemViewModel = ItemViewModel(
            addItem = AddItemUseCase(ItemRepositoryImpl(DataSourceImpl)),
            getItem = GetItemUseCase(ItemRepositoryImpl(DataSourceImpl)),
            getItems = GetItemsUseCase(ItemRepositoryImpl(DataSourceImpl))
        )

        binding.editTitle.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                p0?.let { title ->
                    val description = binding.editDescription.text.toString()
                    itemViewModel.updateIsSaveBtnActive(title.toString(), description)
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        binding.editDescription.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                p0?.let { description ->
                    val title = binding.editTitle.text.toString()
                    itemViewModel.updateIsSaveBtnActive(title, description.toString())
                }
            }
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })

        val itemAdapter = ItemAdapter(emptyList(),
            object : ItemClickListener {
                override fun onClick(position: Int) {
                    itemViewModel.selectItemDetail(position)
                }
            }
        )
        binding.recyclerView.adapter = itemAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)

        lifecycleScope.launch {
            itemViewModel.itemList.collect { list ->
                itemAdapter.updateList(list)
            }
        }

        lifecycleScope.launch {
            itemViewModel.selectedItem.collect { selectedItem ->
                selectedItem?.let {
                    binding.editTitle.setText(it.title)
                    binding.editDescription.setText(it.description)
                }
            }
        }

        lifecycleScope.launch {
            itemViewModel.isSaveBtnActive.collect { isSaveBtnActive ->
                binding.buttonSave.isEnabled = isSaveBtnActive
            }
        }

        binding.buttonSave.setOnClickListener {
            val title = binding.editTitle.text.toString()
            val description = binding.editDescription.text.toString()
            itemViewModel.addItem(title, description)

            binding.editTitle.text.clear()
            binding.editDescription.text.clear()
        }

    }
}