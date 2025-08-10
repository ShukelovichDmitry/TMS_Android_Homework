package com.example.tms_android_homework.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tms_android_homework.databinding.ItemRowBinding
import com.example.tms_android_homework.domain.models.ItemTitleModel
import com.example.tms_android_homework.presentation.listeners.ItemClickListener

class ItemAdapter(
    private var items: List<ItemTitleModel>,
    private val itemClickListener: ItemClickListener,
) : RecyclerView.Adapter<ItemAdapter.ItemViewHolder>() {

    inner class ItemViewHolder(val binding: ItemRowBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(position: Int, item: ItemTitleModel) {
            binding.titleText.text = item.title
            binding.root.setOnClickListener {
                itemClickListener.onClick(position)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val binding = ItemRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ItemViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.bind(position, items[position])

    override fun getItemCount(): Int = items.size

    fun updateList(newItems: List<ItemTitleModel>) {
        items = newItems
        notifyDataSetChanged()
    }
}