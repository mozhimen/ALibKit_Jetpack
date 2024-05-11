package com.mozhimen.abilityk.jetpack.documentfile.utils

import androidx.documentfile.provider.DocumentFile

/**
 * @ClassName DocumentFileUtil
 * @Description TODO
 * @Author Mozhimen & Kolin Zhao
 * @Date 2024/5/11
 * @Version 1.0
 */
fun DocumentFile.isZipped(): Boolean =
    DocumentFileUtil.isZipped(this)

///////////////////////////////////////////////////////////////////////////

object DocumentFileUtil {

    @JvmStatic
    fun isZipped(documentFile: DocumentFile): Boolean =
        documentFile.type == "application/zip"
}