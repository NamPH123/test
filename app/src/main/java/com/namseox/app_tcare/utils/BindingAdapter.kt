package com.namseox.app_tcare.utils

import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.cardview.widget.CardView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.namseox.app_tcare.R
import com.namseox.app_tcare.data.model.LanguageModel

@BindingAdapter("setSrcCheckLanguage")
fun AppCompatImageView.setSrcCheckLanguage(check: Boolean) {
    if (check) {
        this.setImageResource(R.drawable.ic_check_language_true)
    } else {
        this.setImageResource(R.drawable.ic_check_language_false)
    }
}
@BindingAdapter("setText")
fun AppCompatTextView.setText(id: Int) {
    this.text = this.context.getString(id)
}
@BindingAdapter("setBGCV")
fun CardView.setBGCV(check: LanguageModel) {
    if (check.active) {
        this.setBackgroundResource(R.drawable.bg_card_border2)
    } else {
        this.setBackgroundResource(R.drawable.bg_card_border)
    }
}
@BindingAdapter("setImg")
fun AppCompatImageView.setImg(id: Int) {
    Glide.with(this).load(id).into(this)
}
