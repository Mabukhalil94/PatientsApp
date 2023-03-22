package com.mohammad.patients.core

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("app:imageUrl")
fun ImageView.imageUrl(url : String?){
    Glide.with(this.context)
        .load(url)
        .into(this)

}