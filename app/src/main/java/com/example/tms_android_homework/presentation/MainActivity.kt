package com.example.tms_android_homework.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.example.tms_android_homework.R
import com.example.tms_android_homework.data.DataSourceImpl
import com.example.tms_android_homework.data.ItemRepositoryImpl
import com.example.tms_android_homework.domain.models.ItemDetailModel
import com.example.tms_android_homework.domain.usecase.AddItemUseCase
import com.example.tms_android_homework.domain.usecase.GetItemUseCase
import com.example.tms_android_homework.domain.usecase.GetItemsUseCase
import com.example.tms_android_homework.presentation.listeners.AddItemClickListener
import com.example.tms_android_homework.presentation.listeners.ItemClickListener
import com.example.tms_android_homework.presentation.listeners.SaveItemClickListener
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.supervisorScope

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itemViewModel = ItemViewModel(
            addItem = AddItemUseCase(ItemRepositoryImpl(DataSourceImpl)),
            getItem = GetItemUseCase(ItemRepositoryImpl(DataSourceImpl)),
            getItems = GetItemsUseCase(ItemRepositoryImpl(DataSourceImpl))
        )

        itemViewModel.itemList.observe(this, Observer { list ->
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container,
                    ListFragment(
                        list,
                        object : ItemClickListener {
                            override fun onClick(position: Int) {
                                itemViewModel.openItemDetail(position)
                            }
                        },
                        object : AddItemClickListener {
                            override fun onClick() {
                                itemViewModel.openAddItem()
                            }
                        }
                    )
                )
                .commit()
        })

        itemViewModel.openAdd.observe(this, Observer {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container,
                    AddFragment(
                        object : SaveItemClickListener {
                            override fun onClick(title: String, description: String) {
                                itemViewModel.addItem(ItemDetailModel(title, description))
                            }
                        }
                    )
                )
                .addToBackStack(null)
                .commit()
        })

        itemViewModel.selectedItem.observe(this, Observer { selectedItem ->
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, DetailFragment(selectedItem))
                .addToBackStack(null)
                .commit()
        })

    }
}