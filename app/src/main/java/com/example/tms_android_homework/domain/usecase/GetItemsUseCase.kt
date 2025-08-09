package com.example.tms_android_homework.domain.usecase

import com.example.tms_android_homework.domain.ItemRepository
import com.example.tms_android_homework.domain.models.ItemTitleModel

class GetItemsUseCase(private val itemRepository: ItemRepository) {
    fun invoke(): List<ItemTitleModel> {
        return itemRepository.getItems().map { ItemTitleModel(it.title) }
    }
}