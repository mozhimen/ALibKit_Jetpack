-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-dontwarn androidx.room.paging.**
#
-keep public class * extends android.arch.persistence.room.RoomDatabase
-dontwarn android.arch.persistence.room.paging.**
#
-dontwarn android.arch.util.paging.CountedDataSource
-dontwarn android.arch.persistence.room.paging.LimitOffsetDataSource
#
#-keepclassmembers,allowobfuscation class * {
#@com.google.gson.annotations.SerializedName <fields>;
#}