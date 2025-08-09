package com.example.tms_android_homework.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import com.example.tms_android_homework.data.Item
import com.example.tms_android_homework.databinding.FragmentDetailBinding
import com.example.tms_android_homework.domain.models.ItemDetailModel

class DetailFragment(
    private val selectedItem: ItemDetailModel
) : Fragment() {

    private lateinit var binding: FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.titleText.text = selectedItem.title
        binding.descriptionText.text = selectedItem.description

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            requireActivity().supportFragmentManager.popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Toast.makeText(requireContext(), "Going back", Toast.LENGTH_SHORT).show()
    }
}