package com.example.tms_android_homework

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {
    private val _listItem = mutableListOf<String>()

    private val _listState = MutableLiveData(listOf<String>())
    val listState: LiveData<List<String>> get() = _listState

    fun addItem(str: String) {
        _listItem.add(str)
        _listState.value = _listItem
    }

    fun removeItem(position: Int) {
        _listItem.removeAt(position)
        _listState.value = _listItem
    }
}