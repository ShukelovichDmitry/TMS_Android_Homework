package com.example.tms_android_homework.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.example.tms_android_homework.databinding.FragmentAddBinding
import com.example.tms_android_homework.presentation.listeners.SaveItemClickListener

class AddFragment(
    private val saveItemClickListener: SaveItemClickListener
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
        binding.buttonSave.setOnClickListener {
            val title = binding.editTitle.text.toString()
            val description = binding.editDescription.text.toString()
            saveItemClickListener.onClick(title, description)
        }

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        if (binding.editTitle.text.isEmpty() || binding.editDescription.text.isEmpty()) {
            Toast.makeText(requireContext(), "Closed without saving", Toast.LENGTH_SHORT).show()
        }
    }
}