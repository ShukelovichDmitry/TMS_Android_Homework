package com.example.tms_android_homework.data

import com.example.tms_android_homework.domain.ItemRepository

class ItemRepositoryImpl(private val dataSource: DataSource): ItemRepository {
    override fun addItem(item: Item) {
        dataSource.addItem(item)

    }

    override fun getItem(position: Int): Item {
        return dataSource.getItem(position)
    }

    override fun getItems(): List<Item> {
        return dataSource.getItems()
    }

    override fun getItemsCount(): Int {
        return dataSource.getItemsCount()
    }
}