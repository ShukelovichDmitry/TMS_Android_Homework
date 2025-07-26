package com.example.tms_android_homework

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.adapter.FragmentViewHolder
import java.util.LinkedList

class MyAdapter(fragment: FragmentActivity) : FragmentStateAdapter(fragment) {
    private var fragmentCount = 1

    override fun getItemCount(): Int {
        return fragmentCount
    }

    override fun createFragment(position: Int): Fragment {
        return DynamicFragment(position)
    }

    fun addFragment(): Boolean {
        fragmentCount++
        return true
    }

    fun deleteLastFragment(): Boolean {
        if (fragmentCount == 1)
            return false
        fragmentCount--
        return true
    }
}