package com.example.tms_android_homework

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.tms_android_homework.R

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
        parentFragmentManager.setFragmentResultListener(TEXT, this) {
            requestKey, bundle ->
            view.findViewById<TextView>(R.id.result).text = bundle.getString(TEXT)
        }
        view.findViewById<Button>(R.id.to_second).setOnClickListener {
            findNavController().navigate(R.id.first_to_second)
        }
    }
}