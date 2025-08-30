package com.example.tms_android_homework.di

import com.example.tms_android_homework.nbrb.data.NbrbRepositoryImpl
import com.example.tms_android_homework.note.data.NoteRepositoryImpl
import com.example.tms_android_homework.nbrb.domain.NbrbRepository
import com.example.tms_android_homework.note.domain.NoteRepository
import com.example.tms_android_homework.onboarding.data.OnboardingRepositoryImpl
import com.example.tms_android_homework.onboarding.domain.OnboardingRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepoModel {
    @Binds
    @Singleton
    abstract fun bindNoteRepository(
        noteRepositoryImpl: NoteRepositoryImpl
    ): NoteRepository

    @Binds
    @Singleton
    abstract fun bindNbrbRepository(
        nbrbRepositoryImpl: NbrbRepositoryImpl
    ): NbrbRepository

    @Binds
    @Singleton
    abstract fun bindOnboardingRepository(
        onboardingRepositoryImpl: OnboardingRepositoryImpl
    ): OnboardingRepository
}