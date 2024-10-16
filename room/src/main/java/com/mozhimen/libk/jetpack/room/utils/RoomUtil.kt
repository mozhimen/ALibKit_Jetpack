package com.mozhimen.libk.jetpack.room.utils

/**
 * @ClassName RoomUtil
 * @Description TODO
 * @Author Mozhimen / Kolin Zhao
 * @Date 2024/10/15 21:48
 * @Version 1.0
 */
fun String.replaceSqliteSpecialCharacters():String =
    RoomUtil.replaceSqliteSpecialCharacters(this)

/////////////////////////////////////////////////////////////////////////////

object RoomUtil {
    @JvmStatic
    fun replaceSqliteSpecialCharacters(str:String):String{
        return str.replace("'","''")
            .replace("/", "//")
            .replace("[", "/[")
            .replace("]", "/]")
            .replace("%", "/%")
            .replace("&","/&")
            .replace("_", "/_")
            .replace("(", "/(")
            .replace(")", "/)")
    }
}