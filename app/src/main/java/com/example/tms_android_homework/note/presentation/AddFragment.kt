package com.example.tms_android_homework.note.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.bumptech.glide.Glide
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
                setFragmentResult("NOTE", Bundle().apply {
                    putString("TITLE", binding.titleEdit.text.toString())
                    putString("DESCRIPTION", binding.descriptionEdit.text.toString())
                    putString("IMAGE_URL", binding.imageUrlEdit.text.toString())
                })
                parentFragmentManager.popBackStack()
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