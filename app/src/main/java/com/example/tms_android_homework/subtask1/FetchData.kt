package com.example.tms_android_homework.subtask1

import com.example.tms_android_homework.subtask3.IApiManager

class FetchData(
    private val userManager: IUserManager,
    private val apiManager: IApiManager
) {

    fun fetch(userName: String): String {
        var data: String
        try {
            val user = userManager.getUser(userName)
            data = apiManager.fetchDataFromServer(user.id)
        } catch (e: Exception) {
            data = "Ошибка"
        }
        return data
    }
}