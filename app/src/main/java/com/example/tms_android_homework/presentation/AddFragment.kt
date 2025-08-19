package com.example.tms_android_homework.presentation

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.tms_android_homework.R
import com.example.tms_android_homework.databinding.FragmentAddBinding
import com.example.tms_android_homework.presentation.listeners.AddNoteClickListener
import com.example.tms_android_homework.presentation.listeners.SaveNoteClickListener
import com.example.tms_android_homework.presentation.listeners.UrlChangedListener

class AddFragment(
    private val addNoteClickListener: AddNoteClickListener
) : Fragment() {

    private lateinit var binding: FragmentAddBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Glide
            .with(binding.imageView.context)
            .load(R.drawable.ic_no_img)
            .into(binding.imageView)

        binding.imageUrlEdit.addTextChangedListener(UrlChangedListener(binding.imageView))

        binding.saveNoteBtn.setOnClickListener {
            val title = binding.titleEdit.text.toString()
            val description = binding.descriptionEdit.text.toString()
            val imageUrl = binding.imageUrlEdit.text.toString()
            addNoteClickListener.onClick(title, description, imageUrl)
        }
    }
}