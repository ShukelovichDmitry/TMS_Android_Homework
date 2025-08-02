package com.example.tms_android_homework

class FetchData {
    private val userManager = UserManager()
    private val apiManager = ApiManager()

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