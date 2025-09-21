package com.example.tms_android_homework.note.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tms_android_homework.R
import com.example.tms_android_homework.note.data.Note
import com.example.tms_android_homework.note.domain.usecase.AddNoteUseCase
import com.example.tms_android_homework.note.domain.usecase.DeleteNoteUseCase
import com.example.tms_android_homework.note.domain.usecase.EditNoteUseCase
import com.example.tms_android_homework.note.domain.usecase.GetNotesUseCase
import com.example.tms_android_homework.note.domain.usecase.SyncUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import shark.AndroidServices
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val addNote: AddNoteUseCase,
    private val editNote: EditNoteUseCase,
    private val deleteNote: DeleteNoteUseCase,
    private val getNotes: GetNotesUseCase,
    private val sync: SyncUseCase
): ViewModel() {

    private val _noteList = MutableLiveData<List<Note>>(emptyList())
    val noteList: LiveData<List<Note>> = _noteList

    private val _msg = MutableLiveData<Int>()
    val msg: LiveData<Int> = _msg

    private val compositeDisposable = CompositeDisposable()

    fun getNotes() {
        val disposable = getNotes.invoke()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                _noteList.value = it
                //_msg.value = R.string.data_is_received
            }
        compositeDisposable.add(disposable)
    }

    fun addNote(title: String, descriptor: String, imageUrl: String) {
        val disposable = addNote.invoke(title, descriptor, imageUrl)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { _msg.value = R.string.new_note_is_added_in_server },
                { _msg.value = R.string.new_note_is_added_in_db }
            )
        compositeDisposable.add(disposable)
        getNotes()
    }

    fun editNote(id: String, title: String, descriptor: String, imageUrl: String) {
        val disposable = editNote.invoke(id, title, descriptor, imageUrl)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { _msg.value = R.string.note_is_updated_in_server },
                { _msg.value = R.string.note_is_updated_in_db }
            )
        compositeDisposable.add(disposable)
        getNotes()
    }

    fun deleteNote(id: String) {
        val disposable = deleteNote.invoke(id)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { isDeleted ->
                    if (isDeleted) _msg.value = R.string.note_is_deleted_in_server
                    else _msg.value = R.string.note_is_deleted_in_db
                }
            )
        compositeDisposable.add(disposable)
        getNotes()
    }

    fun sync() {
        val disposable = sync.invoke()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe{ isSynced ->
                if (isSynced) _msg.value = R.string.notes_synced
                else _msg.value = R.string.notes_not_synced
            }
        compositeDisposable.add(disposable)
        getNotes()
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}