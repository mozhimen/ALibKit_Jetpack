-keepclassmembers class * extends androidx.webkit.WebViewClientCompat {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
# webview 还要注意native接口
-keepclassmembers class * extends androidx.webkit.WebViewClientCompat {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends androidx.webkit.WebViewClientCompat {
    public void *(android.webkit.WebView, java.lang.String);
}