package com.example.tms_android_homework.di

import android.content.Context
import androidx.room.Room
import com.example.tms_android_homework.note.data.db.NoteDAO
import com.example.tms_android_homework.note.data.db.NoteDatabase
import com.example.tms_android_homework.onboarding.data.DataStoreManager
import dagger.Module
import dagger.Provides

@Module
class DatabaseModule {
    @Provides
    fun provideAppDatabase(context: Context): NoteDatabase {
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
    fun provideDataStoreManager(context: Context): DataStoreManager {
        return DataStoreManager(context)
    }
}