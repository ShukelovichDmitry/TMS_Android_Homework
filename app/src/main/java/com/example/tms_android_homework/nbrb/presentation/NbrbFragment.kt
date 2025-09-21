package com.example.tms_android_homework.nbrb.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.tms_android_homework.databinding.FragmentNbrbBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class NbrbFragment: Fragment() {

    private var binding: FragmentNbrbBinding? = null

    private val nbrbViewModel: NbrbViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNbrbBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        nbrbViewModel.getRates()
        nbrbViewModel.rateJSON.observe(requireActivity(), { json ->
            binding?.ratesText?.text = json
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}