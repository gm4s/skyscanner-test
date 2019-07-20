@file:JvmName("ImageViewExtension")

package io.freshdroid.skyscanner.core.extensions

import android.net.Uri
import android.widget.ImageView
import androidx.annotation.DrawableRes
import io.freshdroid.skyscanner.core.glide.GlideApp

fun ImageView.loadPicture(@DrawableRes resource: Int) {
    GlideApp.with(this)
        .load(resource)
        .into(this)
}

fun ImageView.loadPicture(uri: Uri) {
    GlideApp.with(this)
        .load(uri)
        .into(this)
}

fun ImageView.loadPicture(url: String) {
    GlideApp.with(this)
        .load(url)
        .into(this)
}