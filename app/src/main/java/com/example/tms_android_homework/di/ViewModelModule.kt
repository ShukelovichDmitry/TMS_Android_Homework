package com.example.tms_android_homework.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.tms_android_homework.nbrb.presentation.NbrbViewModel
import com.example.tms_android_homework.note.presentation.NoteViewModel
import com.example.tms_android_homework.onboarding.presentation.OnboardingViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(NoteViewModel::class)
    abstract fun provideNoteViewModel(noteViewModel: NoteViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(NbrbViewModel::class)
    abstract fun provideNbrbViewModel(nbrbViewModel: NbrbViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(OnboardingViewModel::class)
    abstract fun provideOnboardingViewModel(onboardingViewModel: OnboardingViewModel): ViewModel

}