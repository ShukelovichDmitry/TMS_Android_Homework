package com.example.tms_android_homework.data

interface DataSource {
    fun addItem(item: Item)
    fun getItem(position: Int): Item
    fun getItems(): List<Item>
    fun getItemsCount(): Int
}