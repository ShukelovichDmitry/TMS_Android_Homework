package com.example.tms_android_homework

class UserManager {
    val userList = mutableListOf(User(1, "John"))

    fun getUser(userName: String): User {
        val user = userList.find { it.name.equals(userName) }
        if (user == null) {
            throw Exception("Ошибка")
        }
        return user
    }
}