package com.example.tms_android_homework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tms_android_homework.databinding.FirstFragmentBinding

class FirstFragment: Fragment() {
    private lateinit var binding: FirstFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FirstFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }
}