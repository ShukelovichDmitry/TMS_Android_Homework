package com.example.tms_android_homework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController

class FirstFragment: Fragment() {
    companion object {
        val TEXT = "text"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.first_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        view.findViewById<Button>(R.id.to_second).setOnClickListener {
            val text = view.findViewById<EditText>(R.id.text).text.toString()
            val bundle = Bundle()
            bundle.putString(TEXT, text)
            findNavController().navigate(R.id.first_to_second, bundle)
        }
    }
}