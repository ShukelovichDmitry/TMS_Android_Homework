package com.example.tms_android_homework.background.presentation

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InternetConnectionManager @Inject constructor(
    private val context: Context
) {
    suspend fun isOnline() = withContext(Dispatchers.Main) {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE)
                as ConnectivityManager

        val network = connectivityManager.activeNetwork ?: return@withContext false

        val capabilities =
            connectivityManager.getNetworkCapabilities(network) ?: return@withContext false

        capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)
    }
}