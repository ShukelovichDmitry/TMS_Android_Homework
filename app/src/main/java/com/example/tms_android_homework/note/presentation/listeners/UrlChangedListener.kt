package com.example.tms_android_homework.note.presentation.listeners

import android.text.Editable
import android.text.TextWatcher
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.tms_android_homework.R

class UrlChangedListener(private val imageView: ImageView): TextWatcher {
    override fun afterTextChanged(p0: Editable?) {
        p0?.let { imageUrl ->
            Glide
                .with(imageView.context)
                .load(imageUrl.toString())
                .placeholder(R.drawable.ic_no_img)
                .into(imageView)
        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
    }
}