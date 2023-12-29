package com.example.dinnergenerator.adapters

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.dinnergenerator.utils.loadImage


@BindingAdapter("loadImage")
fun loadImages(imageView: ImageView, resource: String?) {
    imageView.loadImage(resource)
}




