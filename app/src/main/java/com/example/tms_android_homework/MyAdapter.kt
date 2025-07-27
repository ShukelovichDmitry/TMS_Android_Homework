package com.example.tms_android_homework

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView
import com.example.tms_android_homework.databinding.LayoutItemBinding

class MyAdapter(private val myViewModel: MyViewModel): RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private var itemList: List<String> = myViewModel.listState.value!!

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
        holder.onBind(itemList[position], { myViewModel.removeItem(position) })

    }

    fun updateList(newItemList: List<String>) {
        //itemList.removeAt(position)
        itemList = newItemList
        notifyDataSetChanged()
    }

}