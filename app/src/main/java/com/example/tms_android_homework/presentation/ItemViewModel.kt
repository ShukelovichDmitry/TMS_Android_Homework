package com.example.tms_android_homework.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tms_android_homework.domain.models.ItemDetailModel
import com.example.tms_android_homework.domain.models.ItemTitleModel
import com.example.tms_android_homework.domain.usecase.AddItemUseCase
import com.example.tms_android_homework.domain.usecase.GetItemUseCase
import com.example.tms_android_homework.domain.usecase.GetItemsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.isActive
import kotlinx.coroutines.launch

class ItemViewModel(
    private val addItem: AddItemUseCase,
    private val getItem: GetItemUseCase,
    private val getItems: GetItemsUseCase
): ViewModel() {

    private val _itemList = MutableStateFlow(getItems.invoke()) //MutableLiveData(getItems.invoke())
    val itemList: StateFlow<List<ItemTitleModel>> //LiveData<List<ItemTitleModel>>
        get() = _itemList

    private val _selectedItem = MutableStateFlow<ItemDetailModel?>(null)//MutableLiveData<ItemDetailModel>()
    val selectedItem: StateFlow<ItemDetailModel?>
        get() = _selectedItem

    private val _isSaveBtnActive = MutableStateFlow(false)
    val isSaveBtnActive: StateFlow<Boolean>
        get() = _isSaveBtnActive

    fun selectItemDetail(position: Int) {
        viewModelScope.launch {
            _selectedItem.emit(getItem.invoke(position))
        }
    }

    fun addItem(title: String, description: String) {
        viewModelScope.launch {
            addItem.invoke(ItemDetailModel(title, description))
            _itemList.emit(getItems.invoke())
        }
    }

    fun updateIsSaveBtnActive(title: String, description: String) {
        viewModelScope.launch {
            if (title.isNotEmpty() && description.isNotEmpty() && !_isSaveBtnActive.value) {
                _isSaveBtnActive.emit(true)
            } else if ((title.isEmpty() || description.isEmpty()) && _isSaveBtnActive.value) {
                _isSaveBtnActive.emit(false)
            }
        }
    }
}