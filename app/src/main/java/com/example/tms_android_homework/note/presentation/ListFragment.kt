package com.example.tms_android_homework.note.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tms_android_homework.R
import com.example.tms_android_homework.databinding.FragmentListBinding
import com.example.tms_android_homework.nbrb.presentation.NbrbFragment
import com.example.tms_android_homework.note.presentation.listeners.DeleteNoteClickListener
import com.example.tms_android_homework.note.presentation.listeners.SaveNoteClickListener
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ListFragment : Fragment() {

    private val noteViewModel: NoteViewModel by viewModels()
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
                        noteViewModel.editNote(id, title, description, imageUrl)
                    }
                },
                object: DeleteNoteClickListener {
                    override fun onClick(id: String) {
                        noteViewModel.deleteNote(id)
                    }
                }
            )
            binding.recyclerView.adapter = noteAdapter
            binding.recyclerView.layoutManager = LinearLayoutManager(context)

            noteViewModel.getNotes()

            lifecycleScope.launch {
                noteViewModel.msg.collect { msg ->
                    if (msg == 0) return@collect
                    Toast.makeText(requireContext(), msg, Toast.LENGTH_SHORT).show()
                }
            }

            lifecycleScope.launch {
                noteViewModel.noteList.collect{ list ->
                    noteAdapter.updateList(list)
                }
            }

            binding.fabAdd.setOnClickListener {
                goToNextScreen(AddFragment())
            }

            binding.fabSync.setOnClickListener {
                noteViewModel.sync()
            }

            binding.goToRates.setOnClickListener {
                goToNextScreen(NbrbFragment())
            }

            setFragmentResultListener("NOTE", { _, bundle ->
                val title = bundle.getString("TITLE").orEmpty()
                val description = bundle.getString("DESCRIPTION").orEmpty()
                val imageUrl = bundle.getString("IMAGE_URL").orEmpty()

                noteViewModel.addNote(title, description, imageUrl)
            })
        }
    }

    private fun goToNextScreen(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .setReorderingAllowed(true)
            .setCustomAnimations(
                R.anim.slide_in_right, R.anim.slide_out_left,
                R.anim.slide_in_left, R.anim.slide_out_right)
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    override fun onDestroy() {
        super.onDestroy()
        binding?.recyclerView?.adapter = null
        binding = null
    }
}