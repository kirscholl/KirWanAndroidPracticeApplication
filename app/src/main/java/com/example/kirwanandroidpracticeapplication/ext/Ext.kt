package com.example.kirwanandroidpracticeapplication.ext

import android.app.Activity
import android.content.Context
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Checkable
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.kirwanandroidpracticeapplication.App
import com.example.kirwanandroidpracticeapplication.BuildConfig
import com.example.kirwanandroidpracticeapplication.R
import com.example.kirwanandroidpracticeapplication.widget.CustomToast
import com.google.android.material.snackbar.Snackbar
import com.just.agentweb.AgentWeb
import com.just.agentweb.DefaultWebClient
import java.text.SimpleDateFormat
import java.util.*

fun Any.loge(content: String?) {
    loge(this.javaClass.simpleName ?: App.TAG, content ?: "")
}

fun loge(tag: String, content: String?) {
    if (BuildConfig.DEBUG) {
        Log.e(tag, content ?: "")
    }
}

fun showToast(content: String) {
    CustomToast(App.context, content).show()
}

fun Fragment.showToast(content: String) {
    CustomToast(this.requireContext(), content).show()
}

fun Context.showToast(content: String) {
    CustomToast(this, content).show()
}

// 扩展点击事件属性(重复点击时长)
var <T : View> T.lastClickTime: Long
    set(value) = setTag(1766613352, value)
    get() = getTag(1766613352) as? Long ?: 0

// 重复点击事件绑定
inline fun <T : View> T.setSingleClickListener(time: Long = 1000, crossinline block: (T) -> Unit) {
    setOnClickListener {
        val currentTimeMillis = System.currentTimeMillis()
        if (currentTimeMillis - lastClickTime > time || this is Checkable) {
            lastClickTime = currentTimeMillis
            block(this)
        }
    }
}
