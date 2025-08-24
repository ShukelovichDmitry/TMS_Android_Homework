package com.example.tms_android_homework.note.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.tms_android_homework.MainActivity
import com.example.tms_android_homework.R
import com.example.tms_android_homework.databinding.FragmentAddBinding
import com.example.tms_android_homework.note.presentation.listeners.UrlChangedListener

class AddFragment: Fragment() {

    private var binding: FragmentAddBinding? = null
    private var urlChangedListener: UrlChangedListener? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAddBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding?.let { binding ->
            Glide
                .with(binding.imageView.context)
                .load(R.drawable.ic_no_img)
                .into(binding.imageView)

            urlChangedListener = UrlChangedListener(binding.imageView)
            binding.imageUrlEdit.addTextChangedListener(urlChangedListener)

            binding.saveNoteBtn.setOnClickListener {
                val title = binding.titleEdit.text.toString()
                val description = binding.descriptionEdit.text.toString()
                val imageUrl = binding.imageUrlEdit.text.toString()

                (requireActivity() as MainActivity).addNote(title, description, imageUrl)
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding?.imageUrlEdit?.removeTextChangedListener(urlChangedListener)
        urlChangedListener = null
        binding = null
    }
}