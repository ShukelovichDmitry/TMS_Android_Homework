package com.example.tms_android_homework.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tms_android_homework.databinding.FragmentNoteDetailBinding

class NoteDetailFragment: Fragment() {
    private lateinit var binding: FragmentNoteDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val position = arguments?.getInt(NoteListFragment.NOTE_ID)

        if (position != null) {
            val note = NoteViewModel.getNote(position)
            binding.newNoteTitle.setText(note.title)
            binding.newNoteText.setText(note.text)

            binding.deleteNote.visibility = View.VISIBLE
            binding.deleteNote.isClickable = true
            binding.deleteNote.setOnClickListener {
                NoteViewModel.removeNote(position)
                findNavController().popBackStack()
            }
        }

        binding.saveNote.setOnClickListener {
            val noteTitle = binding.newNoteTitle.text.toString()
            val noteText = binding.newNoteText.text.toString()
            if (position != null) {
                NoteViewModel.editNote(position, noteTitle, noteText)
            } else {
                NoteViewModel.addNote(noteTitle, noteText)
            }
            findNavController().popBackStack()
        }

        binding.cancel.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}