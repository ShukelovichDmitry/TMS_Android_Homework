package com.example.tms_android_homework.data

object DataSourceImpl: DataSource {
    private val items = mutableListOf<Item>()

    override fun addItem(item: Item) {
        items.add(item)
    }

    override fun getItem(position: Int): Item {
        return items.get(position)
    }

    override fun getItems(): List<Item> {
        return items
    }

    override fun getItemsCount(): Int {
        return items.size
    }
}