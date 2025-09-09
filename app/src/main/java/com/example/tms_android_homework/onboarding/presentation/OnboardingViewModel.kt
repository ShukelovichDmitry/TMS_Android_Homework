package com.example.tms_android_homework.onboarding.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tms_android_homework.di.FeatureScope
import com.example.tms_android_homework.onboarding.domain.usecase.CheckUserSawOnboardingUseCase
import com.example.tms_android_homework.onboarding.domain.usecase.SetUserSawOnboardingUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@FeatureScope
class OnboardingViewModel @Inject constructor(
    private val setUserSawOnboardingUseCase: SetUserSawOnboardingUseCase,
    private val checkUserSawOnboarding: CheckUserSawOnboardingUseCase
) : ViewModel(){

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        println("Возникло исключение в OnboardingViewModel. $exception")
    }

    private val _isOnboardingChecked = MutableStateFlow(false)
    val isOnboardingChecked: StateFlow<Boolean> get() = _isOnboardingChecked.asStateFlow()

    init {
        viewModelScope.launch(coroutineExceptionHandler) {
            val wasSeen = checkUserSawOnboarding.invoke()
            _isOnboardingChecked.emit(wasSeen)
        }
    }

    fun goToNotesList() {
        viewModelScope.launch(coroutineExceptionHandler) {
            setUserSawOnboardingUseCase.invoke()
            _isOnboardingChecked.emit(true)
        }
    }
}