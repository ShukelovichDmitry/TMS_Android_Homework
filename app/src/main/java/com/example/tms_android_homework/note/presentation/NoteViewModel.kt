package com.example.tms_android_homework.note.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tms_android_homework.note.data.Note
import com.example.tms_android_homework.note.domain.usecase.AddNoteUseCase
import com.example.tms_android_homework.note.domain.usecase.DeleteNoteUseCase
import com.example.tms_android_homework.note.domain.usecase.EditNoteUseCase
import com.example.tms_android_homework.note.domain.usecase.GetNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val addNote: AddNoteUseCase,
    private val editNote: EditNoteUseCase,
    private val deleteNote: DeleteNoteUseCase,
    private val getNotes: GetNotesUseCase
): ViewModel() {

    companion object {
        val NewNoteAdded = "Новая запись успешно создана"
        val NewNoteNotAdded = "Не удалось создать новую запись"
        val DataIsRecived = "Данные получены"
        val NoteIsUpdated = "Запись обновлена"
        val NoteIsNotUpdated = "Не удалось обновить запись"
    }

    val CoroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        println("Возникло исключение в NoteViewModel. $exception")
    }

    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    private val _msg = MutableStateFlow("")
    val msg = _msg.asStateFlow()

    fun getNotes() {
        viewModelScope.launch(CoroutineExceptionHandler) {
            val list = getNotes.invoke() ?: emptyList()
            _noteList.emitAll(flow {
                emit(emptyList())
                emit(list)
            })
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