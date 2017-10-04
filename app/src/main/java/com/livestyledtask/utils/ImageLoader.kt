package com.livestyledtask.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

/**
 * Created by ayoola on 04/10/2017.
 */

object ImageLoader {
    fun load(target: ImageView, placeHolder: Int, path: String, context: Context) {
        Glide.with(context).load(path).centerCrop().placeholder(placeHolder).into(target)
    }

}
