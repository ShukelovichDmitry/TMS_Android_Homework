package com.example.tms_android_homework.nbrb.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.tms_android_homework.MainActivity
import com.example.tms_android_homework.databinding.FragmentNbrbBinding
import com.example.tms_android_homework.note.data.Note
import com.example.tms_android_homework.note.presentation.ListFragment.Companion.NOTES

class NbrbFragment: Fragment() {
    companion object {
        val RATES = "RATES"
    }

    private var binding: FragmentNbrbBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNbrbBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity() as MainActivity).getRates()
        parentFragmentManager.setFragmentResultListener(RATES, this) { _, bundle ->
            binding?.ratesText?.text = bundle.getString(RATES).orEmpty()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}