package com.example.tms_android_homework.note.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tms_android_homework.note.data.Note
import com.example.tms_android_homework.note.domain.usecase.AddNoteUseCase
import com.example.tms_android_homework.note.domain.usecase.DeleteNoteUseCase
import com.example.tms_android_homework.note.domain.usecase.EditNoteUseCase
import com.example.tms_android_homework.note.domain.usecase.GetNotesUseCase
import com.example.tms_android_homework.note.domain.usecase.SyncUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val addNote: AddNoteUseCase,
    private val editNote: EditNoteUseCase,
    private val deleteNote: DeleteNoteUseCase,
    private val getNotes: GetNotesUseCase,
    private val sync: SyncUseCase
): ViewModel() {

    private companion object {
        const val DataIsRecived = "Данные получены"
        const val NewNoteAddedInDB = "Новая запись создана в Базе Данных"
        const val NewNoteAddedInServer = "Новая запись создана на сервере"
        const val NoteIsUpdatedInDB = "Запись обновлена в Базе Данных"
        const val NoteIsUpdatedInServer = "Запись обновлена на сервере"
        const val NoteIsDeletedInDB = "Запись удалена из Базы Данных"
        const val NoteIsDeletedInServer = "Запись удалена с сервера"
        const val NotesSynced = "Записи из БД загружены на сервер"
        const val NotesNotSynced = "Не удалось загрузить записи из БД на сервер"
    }

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        println("Возникло исключение в NoteViewModel. $exception")
    }

    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    private val _msg = MutableStateFlow("")
    val msg = _msg.asStateFlow()

    fun getNotes() {
        viewModelScope.launch(coroutineExceptionHandler) {
            val list = getNotes.invoke() ?: emptyList()
            _noteList.emit(list)
            _msg.emit(DataIsRecived)
        }
    }

    fun addNote(title: String, descriptor: String, imageUrl: String) {
        viewModelScope.launch(coroutineExceptionHandler) {
            val newNote = addNote.invoke(title, descriptor, imageUrl)
            if (newNote != null) {
                _msg.emit(NewNoteAddedInDB)
            } else {
                _msg.emit(NewNoteAddedInServer)
            }
            getNotes()
        }
    }

    fun editNote(id: String, title: String, descriptor: String, imageUrl: String) {
        viewModelScope.launch(coroutineExceptionHandler) {
            val updatedNote = editNote.invoke(id, title, descriptor, imageUrl)
            if (updatedNote != null) {
                _msg.emit(NoteIsUpdatedInDB)
            } else {
                _msg.emit(NoteIsUpdatedInServer)
            }
            getNotes()
        }
    }

    fun deleteNote(id: String) {
        viewModelScope.launch(coroutineExceptionHandler) {
            val deletedNote = deleteNote.invoke(id)
            if (deletedNote != null) {
                _msg.emit(NoteIsDeletedInDB)
            } else {
                _msg.emit(NoteIsDeletedInServer)
            }
            getNotes()
        }
    }

    fun sync() {
        viewModelScope.launch(coroutineExceptionHandler) {
            if (sync.invoke())
                _msg.emit(NotesSynced)
            else
                _msg.emit(NotesNotSynced)
        }
    }
}