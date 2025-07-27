package com.example.tms_android_homework

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.tms_android_homework.databinding.LayoutItemBinding
import com.google.android.material.snackbar.Snackbar

class MyAdapter(val itemList: MutableList<String>): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {

    class MyViewHolder(private val binding: LayoutItemBinding): RecyclerView.ViewHolder(binding.root) {
        fun onBind(str: String, removeAction: ()->Unit) {
            binding.itemText.text = str
            binding.deleteItem.setOnClickListener {
                removeAction()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding = LayoutItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MyViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(itemList[position], { removeItem(position) })

    }

    fun addItem(str: String) {
        itemList.add(str)
        notifyItemInserted(itemList.size - 1)
    }

    fun removeItem(position: Int) {
        itemList.removeAt(position)
        notifyItemRemoved(position)
    }

}