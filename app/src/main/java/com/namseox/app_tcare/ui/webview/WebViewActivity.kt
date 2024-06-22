package com.namseox.app_tcare.ui.webview

import android.media.MediaPlayer
import android.webkit.WebViewClient
import com.namseox.app_tcare.R
import com.namseox.app_tcare.databinding.ActivityWebViewBinding
import com.namseox.app_tcare.view.base.AbsBaseActivity

class WebViewActivity: AbsBaseActivity<ActivityWebViewBinding>(false) {


    override fun getFragmentID(): Int = 0

    override fun getLayoutId(): Int = R.layout.activity_web_view

    override fun init() {
        binding.wv.settings.javaScriptEnabled = true
        binding.wv.webViewClient = WebViewClient()
        val url = intent.getStringExtra("data")
        binding.wv.loadUrl(url!!)

    }
}