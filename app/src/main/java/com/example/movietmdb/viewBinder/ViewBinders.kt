package com.example.movietmdb.viewBinder

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import org.imaginativeworld.whynotimagecarousel.ImageCarousel
import org.imaginativeworld.whynotimagecarousel.model.CarouselItem

@BindingAdapter("app:srcUrl")
fun loadImage(view: ImageView, imageUrl: String?) {
    if(!imageUrl.isNullOrEmpty()) {
        Glide.with(view.context).load(imageUrl).into(view)
    }
}

// The IDE does not complete this code, so copy it as it is here
@BindingAdapter("imageList")
fun ImageCarousel.imageList (imageList: List<CarouselItem>?) {
    imageList?.let {
        this.setData(it)
    }
}