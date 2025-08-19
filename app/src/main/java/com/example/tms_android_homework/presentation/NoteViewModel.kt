package com.example.tms_android_homework.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tms_android_homework.data.Note
import com.example.tms_android_homework.domain.usecase.AddNoteUseCase
import com.example.tms_android_homework.domain.usecase.DeleteNoteUseCase
import com.example.tms_android_homework.domain.usecase.EditNoteUseCase
import com.example.tms_android_homework.domain.usecase.GetNotesUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class NoteViewModel(
    private val addNote: AddNoteUseCase,
    private val editNote: EditNoteUseCase,
    private val deleteNote: DeleteNoteUseCase,
    private val getNotes: GetNotesUseCase
): ViewModel() {
    val NewNoteAdded = "Новая запись успешно создана"
    val NewNoteNotAdded = "Не удалось создать новую запись"
    val DataIsRecived = "Данные получены"
    val NoteIsUpdated = "Запись обновлена"
    val NoteIsNotUpdated = "Не удалось обновить запись"

    val CoroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        println("Возникло исключение во ViewModel. $exception")
    }

    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList: StateFlow<List<Note>>
        get() = _noteList

    private val _msg = MutableStateFlow("Приложение запущено")
    val msg: StateFlow<String>
        get() = _msg

    init {
        getNotes()
    }

    fun getNotes() {
        viewModelScope.launch(CoroutineExceptionHandler) {
            val list = getNotes.invoke() ?: emptyList()
            _noteList.emit(list)
            _msg.emit(DataIsRecived)
        }
    }

    fun addNote(title: String, descriptor: String, imageUrl: String) {
        viewModelScope.launch(CoroutineExceptionHandler) {
            val createdNote = addNote.invoke(title, descriptor, imageUrl)
            if(createdNote != null) {
                _msg.emit(NewNoteAdded)
                getNotes()
            } else {
                _msg.emit(NewNoteNotAdded)
            }
        }
    }

    fun editNote(id: String, title: String, descriptor: String, imageUrl: String) {
        viewModelScope.launch(CoroutineExceptionHandler) {
            val updatedNote = editNote.invoke(id, title, descriptor, imageUrl)
            if(updatedNote != null) {
                _msg.emit(NoteIsUpdated)
                getNotes()
            } else {
                _msg.emit(NoteIsNotUpdated)
            }
        }
    }

    fun deleteNote(id: String) {
        viewModelScope.launch(CoroutineExceptionHandler) {
            if(deleteNote.invoke(id)) {
                _msg.emit(NoteIsUpdated)
                getNotes()
            } else {
                _msg.emit(NoteIsNotUpdated)
            }
        }
    }
}