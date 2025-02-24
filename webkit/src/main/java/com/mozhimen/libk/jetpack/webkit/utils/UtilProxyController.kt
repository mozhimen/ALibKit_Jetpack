package com.mozhimen.libk.jetpack.webkit.utils

import android.annotation.SuppressLint
import androidx.webkit.ProxyConfig
import androidx.webkit.ProxyController
import java.util.concurrent.Executor

/**
 * @ClassName UtilProxyController
 * @Description TODO
 * @Author mozhimen
 * @Date 2025/2/24
 * @Version 1.0
 */
@SuppressLint("RequiresFeature")
object UtilProxyController {
    @JvmStatic
    fun get(): ProxyController =
        ProxyController.getInstance()

    /**
     *     wv.setWebViewClient(new WebViewClient() {
     *         @Override
     *         public void onReceivedHttpAuthRequest(WebView view, HttpAuthHandler handler, String host, String realm){
     *             //身份验证（账号密码）
     *             handler.proceed("userName", "password");
     *         }
     *     });
     */
    @JvmStatic
    fun setProxy(proxyRule: String, executor: Executor, listener: Runnable) {
        if (UtilWebViewFeature.isFeatureSupported_PROXY_OVERRIDE()) {
            val proxyConfig: ProxyConfig = ProxyConfig.Builder()
                .addProxyRule(proxyRule)//"111.123.321.121:1234"
                .addDirect().build()
            get().setProxyOverride(proxyConfig, executor, listener)
        }
    }
}