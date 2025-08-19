package com.example.tms_android_homework.presentation

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tms_android_homework.R
import com.example.tms_android_homework.data.Note
import com.example.tms_android_homework.databinding.ItemRowBinding
import com.example.tms_android_homework.presentation.listeners.DeleteNoteClickListener
import com.example.tms_android_homework.presentation.listeners.SaveNoteClickListener
import com.example.tms_android_homework.presentation.listeners.UrlChangedListener

class NoteAdapter(
    private var noteList: List<Note>,
    private val saveNoteClickListener: SaveNoteClickListener,
    private val deleteNoteClickListener: DeleteNoteClickListener
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    inner class NoteViewHolder(val binding: ItemRowBinding) : RecyclerView.ViewHolder(binding.root) {

        fun editBtnClicked(note: Note) {
            binding.viewLayout.visibility = View.GONE
            binding.editLayout.visibility = View.VISIBLE

            binding.titleEdit.setText(note.title)
            binding.descriptionEdit.setText(note.description)
            binding.imageUrlEdit.setText(note.imageUrl)
        }

        fun saveBtnClicked(note: Note) {
            binding.viewLayout.visibility = View.VISIBLE
            binding.editLayout.visibility = View.GONE

            saveNoteClickListener.onClick(note.id,
                binding.titleEdit.text.toString(),
                binding.descriptionEdit.text.toString(),
                binding.imageUrlEdit.text.toString()
            )
        }

        fun cancelBtnClicked() {
            binding.viewLayout.visibility = View.VISIBLE
            binding.editLayout.visibility = View.GONE
        }

        fun bind(note: Note) {
            binding.titleView.setText(note.title)
            binding.descriptionView.setText(note.description)
            Glide
                .with(binding.imageView.context)
                .load(note.imageUrl)
                .placeholder(R.drawable.ic_no_img)
                .into(binding.imageView)

            binding.imageUrlEdit.addTextChangedListener(UrlChangedListener(binding.imageView))

            binding.editNoteBtn.setOnClickListener {
                editBtnClicked(note)
            }
            binding.saveNoteBtn.setOnClickListener {
                saveBtnClicked(note)
            }
            binding.cancelEditNoteBtn.setOnClickListener {
                cancelBtnClicked()
            }
            binding.deleteNoteBtn.setOnClickListener {
                deleteNoteClickListener.onClick(note.id)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) =
        holder.bind(noteList[position])

    override fun getItemCount(): Int = noteList.size

    fun updateList(newNoteList: List<Note>) {
        val diffCallback = DiffCallback(noteList, newNoteList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        noteList = newNoteList
        diffResult.dispatchUpdatesTo(this)
    }
}