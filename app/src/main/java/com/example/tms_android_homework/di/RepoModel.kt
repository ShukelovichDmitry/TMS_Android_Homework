package com.example.tms_android_homework.di

import com.example.tms_android_homework.nbrb.data.NbrbRepositoryImpl
import com.example.tms_android_homework.note.data.NoteRepositoryImpl
import com.example.tms_android_homework.nbrb.domain.NbrbRepository
import com.example.tms_android_homework.note.domain.NoteRepository
import com.example.tms_android_homework.onboarding.data.OnboardingRepositoryImpl
import com.example.tms_android_homework.onboarding.domain.OnboardingRepository
import dagger.Binds
import dagger.Module

@Module
abstract class RepoModel {
    @Binds
    @FeatureScope
    abstract fun bindNoteRepository(
        noteRepositoryImpl: NoteRepositoryImpl
    ): NoteRepository

    @Binds
    @FeatureScope
    abstract fun bindNbrbRepository(
        nbrbRepositoryImpl: NbrbRepositoryImpl
    ): NbrbRepository

    @Binds
    @FeatureScope
    abstract fun bindOnboardingRepository(
        onboardingRepositoryImpl: OnboardingRepositoryImpl
    ): OnboardingRepository
}