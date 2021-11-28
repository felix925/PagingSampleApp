package com.example.pagingsampleapp.presentation

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.pagingsampleapp.R

@BindingAdapter("loadImage")
fun ImageView.loadImage(url: String?) {
    if (url.isNullOrEmpty()) Glide.with(context).load(R.drawable.error_icon).into(this)
    Glide.with(context).load(url).error(R.drawable.error_icon).into(this)
}