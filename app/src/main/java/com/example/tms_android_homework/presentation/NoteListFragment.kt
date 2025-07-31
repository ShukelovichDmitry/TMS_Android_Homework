package com.example.tms_android_homework.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tms_android_homework.R
import com.example.tms_android_homework.databinding.FragmentNoteListBinding

class NoteListFragment: Fragment() {
    private lateinit var binding: FragmentNoteListBinding

    companion object {
        val NOTE_ID = "NOTE_ID"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNoteListBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val noteAdapter = NoteAdapter(emptyList(), this::toDetailPage)
        binding.list.adapter = noteAdapter
        binding.list.layoutManager = LinearLayoutManager(requireContext())

        NoteViewModel.notes.observe(requireActivity(), Observer { list ->
            noteAdapter.updateList(list)
        })

        binding.addNote.setOnClickListener {
            findNavController().navigate(R.id.to_detail)
        }

        binding.removeAllNotes.setOnClickListener {
            NoteViewModel.removeAllNotes()
        }
    }

    fun toDetailPage(id: Int) {
        val bundle = Bundle()
        bundle.putInt(NOTE_ID, id)
        findNavController().navigate(R.id.to_detail, bundle)
    }
}