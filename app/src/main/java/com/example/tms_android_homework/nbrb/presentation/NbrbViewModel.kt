package com.example.tms_android_homework.nbrb.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tms_android_homework.nbrb.domain.usecase.GetRatesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.flow.updateAndGet
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NbrbViewModel @Inject constructor(
    private val getRates: GetRatesUseCase
): ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        println("Возникло исключение в NbrbViewModel. $exception")
    }

    private val _rateJSON = MutableStateFlow("")
    val rateJSON = _rateJSON.asStateFlow()

    fun getRates() {
        viewModelScope.launch(coroutineExceptionHandler) {
            getRates.invoke()?.let { json ->
                _rateJSON.emit(json)
            }
        }
    }
}