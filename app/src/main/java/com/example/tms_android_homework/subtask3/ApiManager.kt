package com.example.tms_android_homework.subtask3

class ApiManager(private val apiClient: IApiClient): IApiManager {

    override fun fetchDataFromServer(userId: Int): String {
        return apiClient.requestData(userId)
    }
}
