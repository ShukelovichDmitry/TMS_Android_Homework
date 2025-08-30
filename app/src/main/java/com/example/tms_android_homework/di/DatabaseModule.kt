package com.example.tms_android_homework.di

import android.content.Context
import androidx.room.Room
import com.example.tms_android_homework.note.data.db.NoteDAO
import com.example.tms_android_homework.note.data.db.NoteDatabase
import com.example.tms_android_homework.onboarding.data.DataStoreManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {
    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): NoteDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            NoteDatabase::class.java,
            "NOTE_DATABASE"
        ).build()
    }

    @Provides
    fun providePostsDAO(appDatabase: NoteDatabase): NoteDAO {
        return appDatabase.getNotesDAO()
    }

    @Provides
    fun provideDataStoreManager(@ApplicationContext context: Context): DataStoreManager {
        return DataStoreManager(context)
    }
}