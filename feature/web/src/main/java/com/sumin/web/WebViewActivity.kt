package com.sumin.web

import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.sumin.navigation.Navigatable
import com.sumin.web.databinding.ActivityWebViewBinding

class WebViewActivity : AppCompatActivity(), Navigatable {
    private val KEY_URL = "hospitalUrl"

    private val binding get() = requireNotNull(_binding)
    private var _binding: ActivityWebViewBinding? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val url = intent.extras?.getString(KEY_URL)
        _binding =
            DataBindingUtil.setContentView(this, R.layout.activity_web_view)

        if (url != null) {
            initView(url)
        } else {
            Toast.makeText(this, "잠시 후 다시 시도해 주세요.", Toast.LENGTH_SHORT).show()
            finish()
        }
    }

    private fun initView(url: String) {
        binding.webView.apply {
            webViewClient = WebViewClient()
            webChromeClient = WebChromeClient()
            settings.loadWithOverviewMode = true
            settings.javaScriptEnabled = true
            settings.useWideViewPort = true
            settings.setSupportZoom(false)
            settings.builtInZoomControls = false
            settings.setSupportMultipleWindows(true)
            settings.domStorageEnabled = true

            loadUrl(url)
        }

        binding.btnClose.setOnClickListener {
            finish()
        }
    }
}