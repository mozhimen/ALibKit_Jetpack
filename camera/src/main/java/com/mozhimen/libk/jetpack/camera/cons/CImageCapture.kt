package com.mozhimen.libk.jetpack.camera.cons

import android.annotation.SuppressLint
import androidx.camera.core.ImageCapture

/**
 * @ClassName CImageCapture
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2024/12/3 0:25
 * @Version 1.0
 */
/**
 * 最小化延迟优化实时性，适合视频流和互动应用。
 * 最大化质量优化图像细节，适合拍照或高质量影像需求。
 * 零快门延迟优化捕捉速度，适合高速动作摄影。
 */
object CImageCapture {
    /**
     * CAPTURE_MODE_MAXIMIZE_QUALITY（最大化质量模式）：
     *
     * 优先考虑图像质量，可能会增加延迟或降低帧率。
     * 适用于需要高质量图像的场景，如摄影、视频制作等。
     * 在此模式下，相机会使用更高的分辨率、更高的动态范围和更精细的色彩处理。
     */

    const val CAPTURE_MODE_MAXIMIZE_QUALITY = ImageCapture.CAPTURE_MODE_MAXIMIZE_QUALITY//5.52MB

    /**
     * CAPTURE_MODE_MINIMIZE_LATENCY（最小化延迟模式）：
     *
     * 主要目标是减少从相机捕捉图像到显示画面之间的延迟。
     * 适用于实时视频传输、游戏或需要即时反应的场景。
     * 这种模式可能会牺牲一些图像质量，尤其是在低光条件下或高分辨率情况下。
     */
    const val CAPTURE_MODE_MINIMIZE_LATENCY = ImageCapture.CAPTURE_MODE_MINIMIZE_LATENCY//4.10MB

    /**
     * CAPTURE_MODE_ZERO_SHUTTER_LAG（零快门延迟模式）：
     *
     * 目的是消除按下快门到图像捕捉之间的延迟。
     * 该模式非常适合拍摄高速动作的场景，如体育赛事、动物拍摄等。
     * 在这种模式下，相机几乎立即捕捉到画面，避免错过关键瞬间。
     */
    @SuppressLint("UnsafeOptInUsageError")
    const val CAPTURE_MODE_ZERO_SHUTTER_LAG = ImageCapture.CAPTURE_MODE_ZERO_SHUTTER_LAG//4.80MB
}