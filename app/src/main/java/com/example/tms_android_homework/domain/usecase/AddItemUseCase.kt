package com.example.tms_android_homework.domain.usecase

import com.example.tms_android_homework.data.Item
import com.example.tms_android_homework.domain.ItemRepository
import com.example.tms_android_homework.domain.models.ItemDetailModel

class AddItemUseCase(private val itemRepository: ItemRepository) {
    fun invoke(newItemModel: ItemDetailModel) {
        val newItemId = itemRepository.getItemsCount() + 1
        itemRepository.addItem(Item(newItemId, newItemModel.title, newItemModel.description))
    }
}