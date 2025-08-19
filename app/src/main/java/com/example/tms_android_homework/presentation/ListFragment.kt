package com.example.tms_android_homework.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tms_android_homework.data.Note
import com.example.tms_android_homework.databinding.FragmentListBinding
import com.example.tms_android_homework.presentation.listeners.BtnClickListener
import com.example.tms_android_homework.presentation.listeners.DeleteNoteClickListener
import com.example.tms_android_homework.presentation.listeners.SaveNoteClickListener
import kotlinx.coroutines.launch

class ListFragment(
    private val noteList: List<Note>,
    private val onSearchBtnClicked: BtnClickListener,
    private val onAddBtnClicked: BtnClickListener,
    private val onSaveClicked: SaveNoteClickListener,
    private val onDeleteClicked: DeleteNoteClickListener,
) : Fragment() {

    private lateinit var binding: FragmentListBinding
    private val noteAdapter = NoteAdapter(
        noteList,
        onSaveClicked,
        onDeleteClicked
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding.recyclerView.adapter = noteAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        binding.fabAdd.setOnClickListener {
            onAddBtnClicked.onClick()
        }

        binding.fabRenew.setOnClickListener {
            onSearchBtnClicked.onClick()
        }
    }

    fun updateList(newList: List<Note>) {
        noteAdapter.updateList(newList)
    }
}