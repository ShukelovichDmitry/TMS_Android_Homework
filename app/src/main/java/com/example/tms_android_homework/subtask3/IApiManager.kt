package com.example.tms_android_homework.subtask3

interface IApiManager {
    fun fetchDataFromServer(userId: Int): String
}