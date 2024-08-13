package com.app.reciperealm.utils

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.Window
import android.widget.ImageView
import com.app.reciperealm.R
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

class LoaderDialog(context: Context) : Dialog(context) {

    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(R.layout.dialog_loader)
        window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))

        // Load GIF into ImageView using Glide
        val gifImageView = findViewById<ImageView>(R.id.loaderGifImageView)
        Glide.with(context)
            .asGif()
            .load(R.drawable.loader)
            .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
            .into(gifImageView)
    }

    override fun show() {
        super.show()
        setCancelable(false)
        setCanceledOnTouchOutside(false)
    }
}
