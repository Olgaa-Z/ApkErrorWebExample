package com.nepwa.webview.nepwamobile

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.widget.SwipeRefreshLayout
import android.support.v7.app.AlertDialog
import android.webkit.WebView
import android.webkit.WebViewClient
import com.android.webview.webviewandroid.R

class MainActivity : AppCompatActivity() {

    lateinit var webView: WebView
    lateinit var swipe: SwipeRefreshLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        swipe = findViewById(R.id.swipe) as SwipeRefreshLayout
        swipe.setOnRefreshListener { WebAction() }

        WebAction()

    }

    fun WebAction() {

        webView = findViewById(R.id.webView) as WebView
        webView.settings.javaScriptEnabled = true
//        webView.settings.setAppCacheEnabled(true)
        webView.loadUrl("http://nepwamobile.com/")
        swipe.isRefreshing = true
        webView.webViewClient = object : WebViewClient() {

            override fun onReceivedError(view: WebView, errorCode: Int, description: String, failingUrl: String) {

                webView.loadUrl("file:///android_asset/error.html")
                AlertDialog.Builder(this@MainActivity)
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .setTitle("Internet Mati")
                    .setMessage("Hidupkan Koneksi Internet !")
//                    .setPositiveButton("OK", { dialog, which -> finish() })
                    .setNegativeButton("OK", null)
                    .show()

            }

            override fun onPageFinished(view: WebView, url: String) {
                // do your stuff here
                swipe.isRefreshing = false
            }

        }

    }
}
