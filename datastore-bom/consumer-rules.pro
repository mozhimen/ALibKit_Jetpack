-keep class androidx.datastore.*.** {*;}
-keepclassmembers class * extends androidx.datastore.preferences.protobuf.GeneratedMessageLite {
    <fields> ;
}