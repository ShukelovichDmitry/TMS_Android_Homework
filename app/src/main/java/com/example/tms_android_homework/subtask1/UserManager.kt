package com.example.tms_android_homework.subtask1

class UserManager: IUserManager {
    val userList = mutableListOf(User(1, "John"))

    override fun getUser(userName: String): User {
        val user = userList.find { it.name.equals(userName) }
        if (user == null) {
            throw Exception("Ошибка")
        }
        return user
    }
}