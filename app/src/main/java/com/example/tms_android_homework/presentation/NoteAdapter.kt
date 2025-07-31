package com.example.tms_android_homework.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tms_android_homework.databinding.LayoutItemBinding
import com.example.tms_android_homework.domain.NoteTitleModel

class NoteAdapter(private var noteList: List<NoteTitleModel>,
                  private val toDetail: (Int) -> Unit): RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    class NoteViewHolder(private val binding: LayoutItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(note: NoteTitleModel, toDetail: () -> Unit) {
            binding.noteTitle.text = note.title
            binding.root.setOnClickListener { toDetail() }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = LayoutItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NoteViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return noteList.size
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        val note = noteList.get(position)
        holder.onBind(note, { toDetail(position) })
    }

    fun updateList(newList: List<NoteTitleModel>) {
        noteList = newList
        notifyDataSetChanged()
    }

}