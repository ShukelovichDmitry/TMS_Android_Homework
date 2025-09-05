package com.example.tms_android_homework.note.presentation

import android.animation.ObjectAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.animation.doOnEnd
import androidx.core.animation.doOnRepeat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.tms_android_homework.R
import com.example.tms_android_homework.databinding.ItemRowBinding
import com.example.tms_android_homework.note.data.Note
import com.example.tms_android_homework.note.presentation.listeners.DeleteNoteClickListener
import com.example.tms_android_homework.note.presentation.listeners.SaveNoteClickListener
import com.example.tms_android_homework.note.presentation.listeners.UrlChangedListener


class NoteAdapter(
    saveNoteClickListener: SaveNoteClickListener,
    deleteNoteClickListener: DeleteNoteClickListener
) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {

    private var noteList: List<Note>? = emptyList()

    private var saveNoteBtnClicked: SaveNoteClickListener? = saveNoteClickListener
    private var deleteNoteBtnClicked: DeleteNoteClickListener? = deleteNoteClickListener

    inner class NoteViewHolder(private var binding: ItemRowBinding)
        : RecyclerView.ViewHolder(binding.root) {

        private var urlChangedListener: UrlChangedListener? = UrlChangedListener(binding.imageView)

        private fun changeModeAnimation(enableEdit: Boolean) {

            ObjectAnimator.ofFloat(binding.root, "scaleX", 1f, 0f).apply {
                duration = 500
                repeatCount = 1
                repeatMode = ObjectAnimator.REVERSE

                doOnRepeat {
                    if (enableEdit) {
                        binding.viewLayout.visibility = View.GONE
                        binding.editLayout.visibility = View.VISIBLE
                    } else {
                        binding.viewLayout.visibility = View.VISIBLE
                        binding.editLayout.visibility = View.GONE
                    }
                }
            }.start()
        }

        private fun deleteBtnClicked(id: String) {
            ObjectAnimator.ofFloat(binding.root, "alpha", 1f, 0f).apply {
                duration = 500
                doOnEnd {
                    deleteNoteBtnClicked?.onClick(id)
                }
            }.start()
        }

        fun editBtnClicked(note: Note) {
            binding.titleEdit.setText(note.title)
            binding.descriptionEdit.setText(note.description)
            binding.imageUrlEdit.setText(note.imageUrl)

            changeModeAnimation(true)
        }

        fun saveBtnClicked(note: Note) {
            changeModeAnimation(false)

            saveNoteBtnClicked?.onClick(note.id,
                binding.titleEdit.text.toString(),
                binding.descriptionEdit.text.toString(),
                binding.imageUrlEdit.text.toString()
            )
        }

        fun cancelBtnClicked() {
            changeModeAnimation(false)
        }

        fun bind(note: Note) {
            binding.titleView.text = note.title
            binding.descriptionView.text = note.description
            Glide
                .with(binding.imageView.context)
                .load(note.imageUrl)
                .placeholder(R.drawable.ic_no_img)
                .into(binding.imageView)

            binding.imageUrlEdit.addTextChangedListener(urlChangedListener)

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
                deleteBtnClicked(note.id)
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NoteViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        noteList?.let { noteList ->
            holder.bind(noteList[position])
        }
    }

    override fun getItemCount(): Int = noteList?.size ?: 0

    override fun onDetachedFromRecyclerView(recyclerView: RecyclerView) {
        super.onDetachedFromRecyclerView(recyclerView)
        noteList = null
        saveNoteBtnClicked = null
        deleteNoteBtnClicked = null
    }

    fun updateList(newNoteList: List<Note>) {
        val diffCallback = DiffCallback(noteList.orEmpty(), newNoteList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        noteList = newNoteList
        diffResult.dispatchUpdatesTo(this)
    }
}