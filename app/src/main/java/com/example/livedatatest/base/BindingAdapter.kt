package com.example.livedatatest.base

import android.view.View
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import com.example.livedatatest.R
import com.example.livedatatest.features.detail.domain.interactor.makeSpannable

@BindingAdapter("visibility")
fun View.setVisibility(visible: Boolean) {
    visibility = if (visible) View.VISIBLE else View.GONE
}

@BindingAdapter(value = ["url", "imageRadius"], requireAll = false)
fun setImageUrl(view: AppCompatImageView, url: String?, radius: Int? = null) {
    if (url.isNullOrEmpty()) {
        return
    } else {
        val request = Glide.with(view.context)
            .load(url)
            .centerCrop()

        radius?.let {
            if (it > 0) {
                request.apply(RequestOptions.bitmapTransform(RoundedCorners(it)))
            }
        }
    }
}

@BindingAdapter("android:src")
fun setImageSource(view: AppCompatImageView, resId: Int) {
    view.setImageResource(resId)
}

@BindingAdapter("span")
fun makeSpannable(view: AppCompatTextView, span: String?) {
    span?.let {
        val text = view.text.toString()
        // makeSpannable() : features.detail.domain.interactor.SpannableGenerator.kt에 있음
        val spanned = text.makeSpannable(
            ContextCompat.getColor(view.context, R.color.green),
            span
        )

        view.text = spanned
    }
}