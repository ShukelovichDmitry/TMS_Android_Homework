package com.example.tms_android_homework

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.tms_android_homework.nbrb.presentation.NbrbFragment
import com.example.tms_android_homework.nbrb.presentation.NbrbViewModel
import com.example.tms_android_homework.note.data.Note
import com.example.tms_android_homework.note.presentation.AddFragment
import com.example.tms_android_homework.note.presentation.ListFragment
import com.example.tms_android_homework.note.presentation.NoteViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val noteViewModel: NoteViewModel by viewModels()
    private val nbrbViewModel: NbrbViewModel by viewModels()

    val CoroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        println("Возникло исключение в MainActivity. $exception")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setNoteListUpdated()
        setRatesJSONUpdated()
        setMsgUpdated()

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, ListFragment())
            .commit()
    }

    fun goToAddFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, AddFragment())
            .addToBackStack(null)
            .commit()
    }

    fun goToNbrbFragment() {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, NbrbFragment())
            .addToBackStack(null)
            .commit()
    }

    fun setNoteListUpdated() {
        lifecycleScope.launch(CoroutineExceptionHandler) {
            noteViewModel.noteList.collect { list ->
                supportFragmentManager.setFragmentResult(
                    ListFragment.NOTES,
                    Bundle().apply { putParcelableArrayList(ListFragment.NOTES, ArrayList<Note>(list)) }
                )
            }
        }
    }

    fun setRatesJSONUpdated() {
        lifecycleScope.launch(CoroutineExceptionHandler) {
            nbrbViewModel.rateJSON.collect { json ->
                supportFragmentManager.setFragmentResult(
                    NbrbFragment.RATES,
                    Bundle().apply { putString(NbrbFragment.RATES, json) }
                )
            }
        }
    }

    fun setMsgUpdated() {
        lifecycleScope.launch(CoroutineExceptionHandler) {
            noteViewModel.msg.collect { msg ->
                if (msg.isEmpty()) return@collect
                Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun getRates() {
        nbrbViewModel.getRates()
    }

    fun getNotes() {
        noteViewModel.getNotes()
    }

    fun addNote(title: String, description: String, imageUrl: String) {
        noteViewModel.addNote(title, description, imageUrl)
        supportFragmentManager.popBackStack()
    }

    fun saveEditNote(id: String, title: String, description: String, imageUrl: String) {
        noteViewModel.editNote(id, title, description, imageUrl)
    }

    fun deleteNote(id: String) {
        noteViewModel.deleteNote(id)
    }
}