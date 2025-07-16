package com.example.tms_android_homework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class SecondFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.second_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Button>(R.id.send_result).setOnClickListener {
            val text = view.findViewById<EditText>(R.id.text).text.toString()
            parentFragmentManager.setFragmentResult(FirstFragment.TEXT, bundleOf(FirstFragment.TEXT to text))
            findNavController().navigate(R.id.second_to_first)
        }
    }
}