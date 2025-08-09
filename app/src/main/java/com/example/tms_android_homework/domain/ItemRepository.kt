package com.example.tms_android_homework.domain

import com.example.tms_android_homework.data.Item

interface ItemRepository {
    fun addItem(item: Item)
    fun getItem(position: Int): Item
    fun getItems(): List<Item>
    fun getItemsCount(): Int
}