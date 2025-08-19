package com.example.tms_android_homework.presentation

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.tms_android_homework.R
import com.example.tms_android_homework.data.NoteRepositoryImpl
import com.example.tms_android_homework.data.RetrofitInstance
import com.example.tms_android_homework.domain.usecase.AddNoteUseCase
import com.example.tms_android_homework.domain.usecase.DeleteNoteUseCase
import com.example.tms_android_homework.domain.usecase.EditNoteUseCase
import com.example.tms_android_homework.domain.usecase.GetNotesUseCase
import com.example.tms_android_homework.presentation.listeners.BtnClickListener
import com.example.tms_android_homework.presentation.listeners.AddNoteClickListener
import com.example.tms_android_homework.presentation.listeners.DeleteNoteClickListener
import com.example.tms_android_homework.presentation.listeners.SaveNoteClickListener
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    val CoroutineExceptionHandler = CoroutineExceptionHandler { _, exception ->
        println("Возникло исключение в MainActivity. $exception")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val noteViewModel = NoteViewModel(
            addNote = AddNoteUseCase(NoteRepositoryImpl(RetrofitInstance)),
            getNotes = GetNotesUseCase(NoteRepositoryImpl(RetrofitInstance)),
            editNote = EditNoteUseCase(NoteRepositoryImpl(RetrofitInstance)),
            deleteNote = DeleteNoteUseCase(NoteRepositoryImpl(RetrofitInstance)),
        )

        val addFragment = AddFragment(
            object : AddNoteClickListener {
                override fun onClick(title: String, description: String, imageUrl: String) {
                    supportFragmentManager.popBackStack()
                    noteViewModel.addNote(title, description, imageUrl)
                }
            }
        )

        val listFragment = ListFragment(
            noteViewModel.noteList.value,
            object : BtnClickListener {
                override fun onClick() {
                    noteViewModel.getNotes()
                }
            },
            object : BtnClickListener {
                override fun onClick() {
                    supportFragmentManager.beginTransaction()
                        .replace(R.id.fragment_container, addFragment)
                        .addToBackStack(null)
                        .commit()
                }
            },
            object : SaveNoteClickListener {
                override fun onClick(id: String, title: String, descriptor: String, imageUrl: String) {
                    noteViewModel.editNote(id, title, descriptor, imageUrl)
                }
            },
            object : DeleteNoteClickListener {
                override fun onClick(id: String) {
                    noteViewModel.deleteNote(id)
                }
            }
        )

        lifecycleScope.launch(CoroutineExceptionHandler) {
            noteViewModel.noteList.collect { list ->
                listFragment.updateList(list)
            }
        }

        lifecycleScope.launch(CoroutineExceptionHandler) {
            noteViewModel.msg.collect { msg ->
                Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
            }
        }

        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, listFragment)
            .commit()

    }
}