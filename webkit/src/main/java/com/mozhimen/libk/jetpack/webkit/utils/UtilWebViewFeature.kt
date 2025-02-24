package com.mozhimen.libk.jetpack.webkit.utils

import androidx.webkit.WebViewFeature
import com.mozhimen.libk.jetpack.webkit.cons.CWebViewFeature

/**
 * @ClassName UtilWebViewFeature
 * @Description TODO
 * @Author mozhimen
 * @Date 2025/2/24
 * @Version 1.0
 */
object UtilWebViewFeature {
    @JvmStatic
    fun isFeatureSupported(feature: String): Boolean =
        WebViewFeature.isFeatureSupported(feature/*CWebViewFeature.PROXY_OVERRIDE*/)

    @JvmStatic
    fun isFeatureSupported_PROXY_OVERRIDE(): Boolean =
        isFeatureSupported(CWebViewFeature.PROXY_OVERRIDE)
}