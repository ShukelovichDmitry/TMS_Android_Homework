package com.example.tms_android_homework.domain.usecase

import com.example.tms_android_homework.domain.ItemRepository
import com.example.tms_android_homework.domain.models.ItemDetailModel

class GetItemUseCase(private val itemRepository: ItemRepository) {
    fun invoke(position: Int): ItemDetailModel {
        val item = itemRepository.getItem(position)
        return ItemDetailModel(item.title, item.description)
    }
}