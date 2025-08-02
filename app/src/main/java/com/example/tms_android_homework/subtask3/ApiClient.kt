package com.example.tms_android_homework.subtask3

class ApiClient: IApiClient {
    override fun requestData(userId: Int): String {
        return "Data for $userId"
    }
}
