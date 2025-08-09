package com.example.tms_android_homework.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tms_android_homework.databinding.FragmentListBinding
import com.example.tms_android_homework.domain.models.ItemTitleModel
import com.example.tms_android_homework.presentation.listeners.AddItemClickListener
import com.example.tms_android_homework.presentation.listeners.ItemClickListener

class ListFragment(
    private val list: List<ItemTitleModel>,
    private val onItemSelected: ItemClickListener,
    private val onAddItem: AddItemClickListener
) : Fragment() {

    private lateinit var binding: FragmentListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?,
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val itemAdapter = ItemAdapter(list, onItemSelected)
        binding.recyclerView.adapter = itemAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(context)

        binding.fabAdd.setOnClickListener {
            onAddItem.onClick()
        }
    }
}