package com.example.tms_android_homework.data

import com.example.tms_android_homework.domain.ItemRepository

class ItemRepositoryImpl(private val dataSource: DataSource): ItemRepository {
    private var _lastId = 0

    override fun addItem(item: Item) {
        _lastId = item.id
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

    override fun getLastId(): Int {
        return _lastId
    }
}