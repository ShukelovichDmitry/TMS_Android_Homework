package com.example.tms_android_homework.nbrb.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.tms_android_homework.App
import com.example.tms_android_homework.databinding.FragmentNbrbBinding
import kotlinx.coroutines.launch
import javax.inject.Inject

class NbrbFragment: Fragment() {
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private var binding: FragmentNbrbBinding? = null

    private val nbrbViewModel: NbrbViewModel by viewModels{viewModelFactory}

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        binding = FragmentNbrbBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        (requireActivity().application as App).provideAppComponent().inject(this)
        nbrbViewModel.getRates()
        lifecycleScope.launch {
            nbrbViewModel.rateJSON.collect { json ->
                binding?.ratesText?.text = json
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        binding = null
    }
}