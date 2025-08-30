package com.example.tms_android_homework.onboarding.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

val Context.dataStore by preferencesDataStore(name = "datastore_prefs")

class DataStoreManager @Inject constructor(private val context: Context) {

    companion object {
        private val ONBOARDING_KEY = booleanPreferencesKey("datastore_onboarding_key")
    }

    fun wasOnboardingSeen(): Flow<Boolean> {
        return context.dataStore.data.map {
            it[ONBOARDING_KEY] ?: false
        }
    }

    suspend fun setUserSawOnboarding() {
        context.dataStore.edit {
            it[ONBOARDING_KEY] = true
        }
    }

    suspend fun setUserSawOnboarding(wasSeen: Boolean) {
        context.dataStore.edit {
            it[ONBOARDING_KEY] = wasSeen
        }
    }
}