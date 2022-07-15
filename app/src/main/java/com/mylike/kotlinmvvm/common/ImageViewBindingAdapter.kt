package com.mylike.kotlinmvvm.common

import android.graphics.Bitmap
import android.text.TextUtils
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.mylike.kotlinmvvm.R
import com.mylike.kotlinmvvm.utils.LogUtils
import com.squareup.picasso.Picasso

/**
 * Created by ydh on 2022/7/14
 *
 */
class ImageViewBindingAdapter {
    companion object {
        @JvmStatic
        @BindingAdapter("image")
        fun setImage(imageView: ImageView, url: String) {
            LogUtils.e("第一个")
            Picasso.with(imageView.context)
                .load(url)
                .error(R.mipmap.default_header)
                .config(Bitmap.Config.RGB_565)
                .into(imageView)
        }

        /**
         *第一个和第二个同时存在的话，走的是第二个
         */
        @JvmStatic
        @BindingAdapter(value = ["image", "defaultImage"], requireAll = false)
        fun setImage(imageView: ImageView, url: String?, resId: Int) {
            LogUtils.e("第二个")
            if (TextUtils.isEmpty(url)) {
                imageView.setImageResource(resId)
            } else {
                Picasso.with(imageView.context)
                    .load(url)
                    .error(R.mipmap.default_header)
                    .config(Bitmap.Config.RGB_565)
                    .into(imageView)
            }

        }

        @JvmStatic
        @BindingAdapter("image")
        fun setImage(imageView: ImageView, resId: Int) {
            LogUtils.e("第三个")
            imageView.setImageResource(resId)
        }

    }
}