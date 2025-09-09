package com.example.tms_android_homework.note.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tms_android_homework.R
import com.example.tms_android_homework.di.FeatureScope
import com.example.tms_android_homework.note.data.Note
import com.example.tms_android_homework.note.domain.usecase.AddNoteUseCase
import com.example.tms_android_homework.note.domain.usecase.DeleteNoteUseCase
import com.example.tms_android_homework.note.domain.usecase.EditNoteUseCase
import com.example.tms_android_homework.note.domain.usecase.GetNotesUseCase
import com.example.tms_android_homework.note.domain.usecase.SyncUseCase
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@FeatureScope
class NoteViewModel @Inject constructor(
    private val addNote: AddNoteUseCase,
    private val editNote: EditNoteUseCase,
    private val deleteNote: DeleteNoteUseCase,
    private val getNotes: GetNotesUseCase,
    private val sync: SyncUseCase
): ViewModel() {

    private val coroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        println("Возникло исключение в NoteViewModel. $exception")
    }

    private val _noteList = MutableStateFlow<List<Note>>(emptyList())
    val noteList = _noteList.asStateFlow()

    private val _msg = MutableStateFlow(0)
    val msg = _msg.asStateFlow()

    fun getNotes() {
        viewModelScope.launch(coroutineExceptionHandler) {
            val list = getNotes.invoke()?: emptyList()
            _noteList.emit(list)
            _msg.emit(R.string.data_is_received)
        }
    }

    fun addNote(title: String, descriptor: String, imageUrl: String) {
        viewModelScope.launch(coroutineExceptionHandler) {
            val newNote = addNote.invoke(title, descriptor, imageUrl)
            if (newNote != null) {
                _msg.emit(R.string.new_note_is_added_in_server)
            } else {
                _msg.emit(R.string.new_note_is_added_in_db)
            }
            getNotes()
        }
    }

    fun editNote(id: String, title: String, descriptor: String, imageUrl: String) {
        viewModelScope.launch(coroutineExceptionHandler) {
            val updatedNote = editNote.invoke(id, title, descriptor, imageUrl)
            if (updatedNote != null) {
                _msg.emit(R.string.note_is_updated_in_server)
            } else {
                _msg.emit(R.string.note_is_updated_in_db)
            }
            getNotes()
        }
    }

    fun deleteNote(id: String) {
        viewModelScope.launch(coroutineExceptionHandler) {
            if (deleteNote.invoke(id)) {
                _msg.emit(R.string.note_is_deleted_in_server)
            } else {
                _msg.emit(R.string.note_is_deleted_in_db)
            }
            getNotes()
        }
    }

    fun sync() {
        viewModelScope.launch(coroutineExceptionHandler) {
            if (sync.invoke())
                _msg.emit(R.string.notes_synced)
            else
                _msg.emit(R.string.notes_not_synced)
        }
    }
}