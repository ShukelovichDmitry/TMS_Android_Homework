package com.example.tms_android_homework

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MyViewModel: ViewModel() {
    private val _listItem = mutableListOf<String>()

    private val _listLiveData = MutableLiveData(listOf<String>())
    val listLiveData: LiveData<List<String>> get() = _listLiveData

    fun addItem(str: String) {
        _listItem.add(str)
        _listLiveData.value = _listItem
    }

    fun removeItem(position: Int) {
        _listItem.removeAt(position)
        _listLiveData.value = _listItem
    }

}