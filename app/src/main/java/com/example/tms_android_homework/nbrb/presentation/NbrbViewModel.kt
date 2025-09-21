package com.example.tms_android_homework.nbrb.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.tms_android_homework.nbrb.domain.usecase.GetRatesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.disposables.CompositeDisposable
import javax.inject.Inject

@HiltViewModel
class NbrbViewModel @Inject constructor(
    private val getRates: GetRatesUseCase
): ViewModel() {

    private val _rateJSON = MutableLiveData<String>()
    val rateJSON: LiveData<String> = _rateJSON

    private val compositeDisposable = CompositeDisposable()

    fun getRates() {
        val disposable = getRates.invoke().subscribe { json ->
            _rateJSON.value = json
        }
        compositeDisposable.add(disposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}