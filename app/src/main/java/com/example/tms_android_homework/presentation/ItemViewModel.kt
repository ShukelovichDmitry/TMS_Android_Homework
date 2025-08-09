package com.example.tms_android_homework.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tms_android_homework.domain.models.ItemDetailModel
import com.example.tms_android_homework.domain.models.ItemTitleModel
import com.example.tms_android_homework.domain.usecase.AddItemUseCase
import com.example.tms_android_homework.domain.usecase.GetItemUseCase
import com.example.tms_android_homework.domain.usecase.GetItemsUseCase

class ItemViewModel(
    private val addItem: AddItemUseCase,
    private val getItem: GetItemUseCase,
    private val getItems: GetItemsUseCase
): ViewModel() {

    private val _itemList = MutableLiveData(getItems.invoke())
    val itemList: LiveData<List<ItemTitleModel>>
        get() = _itemList

    private val _openAdd = MutableLiveData<Boolean>()
    val openAdd: LiveData<Boolean>
        get() = _openAdd

    private val _selectedItem = MutableLiveData<ItemDetailModel>()
    val selectedItem: LiveData<ItemDetailModel>
        get() = _selectedItem

    fun openItemDetail(position: Int) {
        _selectedItem.value = getItem.invoke(position)
    }

    fun openAddItem() {
        _openAdd.value = true
    }

    fun addItem(newItem: ItemDetailModel) {
        addItem.invoke(newItem)
        _itemList.value = getItems.invoke()
    }
}