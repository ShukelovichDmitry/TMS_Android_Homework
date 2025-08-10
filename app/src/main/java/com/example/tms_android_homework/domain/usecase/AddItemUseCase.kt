package com.example.tms_android_homework.domain.usecase

import com.example.tms_android_homework.data.Item
import com.example.tms_android_homework.domain.ItemRepository
import com.example.tms_android_homework.domain.models.ItemDetailModel
import kotlin.random.Random

class AddItemUseCase(private val itemRepository: ItemRepository) {
    fun invoke(newItemModel: ItemDetailModel) {
        val newItemId = itemRepository.getLastId() + Random.nextInt(10)
        itemRepository.addItem(Item(newItemId, newItemModel.title, newItemModel.description))
    }
}