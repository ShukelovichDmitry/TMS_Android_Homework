package com.example.tms_android_homework.note.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tms_android_homework.MainActivity
import com.example.tms_android_homework.note.data.Note
import com.example.tms_android_homework.databinding.FragmentListBinding
import com.example.tms_android_homework.note.presentation.listeners.DeleteNoteClickListener
import com.example.tms_android_homework.note.presentation.listeners.SaveNoteClickListener

class ListFragment : Fragment() {

    companion object {
        val NOTES = "NOTES"
    }

    private var binding: FragmentListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        binding?.let { binding ->

            val noteAdapter = NoteAdapter(
                object: SaveNoteClickListener {
                    override fun onClick(id: String, title: String, description: String, imageUrl: String) {
                        (requireActivity() as MainActivity).saveEditNote(id, title, description, imageUrl)
                    }
                },
                object: DeleteNoteClickListener {
                    override fun onClick(id: String) {
                        (requireActivity() as MainActivity).deleteNote(id)
                    }
                }
            )
            binding.recyclerView.adapter = noteAdapter
            binding.recyclerView.layoutManager = LinearLayoutManager(context)

            (requireActivity() as MainActivity).getNotes()

            binding.fabAdd.setOnClickListener {
                (requireActivity() as MainActivity).goToAddFragment()
            }

            binding.goToRates.setOnClickListener {
                (requireActivity() as MainActivity).goToNbrbFragment()
            }

            parentFragmentManager.setFragmentResultListener(NOTES, this) { _, bundle ->
                val newList = bundle.getParcelableArrayList(NOTES, Note::class.java)
                newList?.let { newList ->
                    noteAdapter.updateList(newList)
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding?.recyclerView?.adapter = null
        binding = null
    }
}