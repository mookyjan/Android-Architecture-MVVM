package com.mudassirkhan.realitygametask.util

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import android.util.Base64
import android.view.View
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.mudassirkhan.realitygametask.R

/**
 * Created by Mudassir on 1/5/2019.
 */


@BindingAdapter("android:background")
fun setBackground(view: View, drawable: Drawable?) {
    drawable?.let {
        view.background = drawable
    }
}


@BindingAdapter("srcDrawable")
fun setSrcDrawable(view: ImageView, url: String?) {
    if (!url.isNullOrEmpty()) {
        view.visibility = View.VISIBLE
        val resId = view.resources.getIdentifier(url, "drawable", view.context.packageName)
        val drawable = ContextCompat.getDrawable(view.context, resId)
        view.setImageDrawable(drawable)
    } else {
        view.visibility = View.GONE
    }
}

@BindingAdapter("srcEvent")
fun setSrcEvent(view: ImageView, url: String?) {
    if (!url.isNullOrBlank()) {
        Glide.with(view).load(url).into(view)
    }
}

@BindingAdapter("srcBase64")
fun setSrcBaseDrawable(view: ImageView, base64: String?) {
    try {
        if (!base64.isNullOrEmpty()) {
            view.visibility = View.VISIBLE
            val decodeString = Base64.decode(base64, Base64.NO_WRAP)
            val decodedByte = BitmapFactory.decodeByteArray(decodeString, 0, decodeString.size)
            val scaledBitmap =
                Bitmap.createScaledBitmap(decodedByte, decodedByte.width / 2, decodedByte.height / 2, true)
            view.setImageBitmap(scaledBitmap)
//        val resId = view.resources.getIdentifier(base64, "drawable", view.context.packageName)
//        val drawable = ContextCompat.getDrawable(view.context, resId)
//        view.setImageDrawable(drawable)
        } else {
            view.visibility = View.GONE
        }
    } catch (e: Exception) {
        e.printStackTrace()
    }
}


@BindingAdapter("android:src")
fun setImageUrl(view: ImageView, url: String?) {
    Glide.with(view).load(url).placeholder(R.drawable.img)
        .error(R.mipmap.ic_launcher).centerInside().into(view)
}

@BindingAdapter("android:src")
fun setImageUrl(imageView: ImageView, resource: Int) {
    if (resource != 0) {
        imageView.setImageResource(resource)
    }
}

@BindingAdapter("android:src2")
fun setImageUrl2(view: ImageView, url: String?) {
    Glide.with(view).load(url).error(R.mipmap.ic_launcher).centerInside().into(view)
}

@BindingAdapter("bind:imageBitmap")
fun setBitmap(view: ImageView, bitmap: Bitmap) {
    view.setImageBitmap(bitmap)

}


