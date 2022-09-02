package com.example.pokemon.utils

import android.widget.ImageView
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.pokemon.R


fun ImageView.setImageFromUrl(
    url: String?,
    circularProgressDrawable: CircularProgressDrawable,
) {

    val options = RequestOptions()
        .placeholder(circularProgressDrawable)
        .error(R.mipmap.ic_launcher_round)


    Glide
        .with(context)
        .setDefaultRequestOptions(options)
        .load(url)
        .into(this)

}

