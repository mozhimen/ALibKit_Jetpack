package com.mozhimen.libk.jetpack.window.utils

import androidx.window.layout.DisplayFeature
import androidx.window.layout.FoldingFeature
import androidx.window.layout.WindowLayoutInfo

/**
 * @ClassName WindowLayoutInfoUtil
 * @Description TODO
 * @Author mozhimen
 * @Date 2025/2/6
 * @Version 1.0
 */
object WindowLayoutInfoUtil {
    @JvmStatic
    fun getDisplayFeatures(windowLayoutInfo: WindowLayoutInfo): List<DisplayFeature> =
        windowLayoutInfo.displayFeatures

    /**
     * // New posture information.
     * // Use information from the foldingFeature object.
     */
    @JvmStatic
    fun getDisplayFeature_folding(windowLayoutInfo: WindowLayoutInfo) =
        getDisplayFeatures(windowLayoutInfo).filterIsInstance<FoldingFeature>().firstOrNull()
}