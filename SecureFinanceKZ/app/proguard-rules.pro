# Room
-keep class * extends androidx.room.RoomDatabase
-keep @androidx.room.Entity class *
-dontwarn androidx.room.paging.**

# SQLCipher
-keep class net.sqlcipher.** { *; }
-keep class net.sqlcipher.database.** { *; }

# Security Crypto
-keep class androidx.security.crypto.** { *; }

# Gson
-keepattributes Signature
-keepattributes *Annotation*
-dontwarn sun.misc.**
-keep class com.google.gson.** { *; }
-keep class * implements com.google.gson.TypeAdapterFactory
-keep class * implements com.google.gson.JsonSerializer
-keep class * implements com.google.gson.JsonDeserializer

# MPAndroidChart
-keep class com.github.mikephil.charting.** { *; }

# Backup data classes
-keep class kz.diploma.securefinance.data.entity.** { *; }
-keep class kz.diploma.securefinance.data.backup.** { *; }

# Kotlin
-keep class kotlin.Metadata { *; }
-dontwarn kotlin.**

# Missing annotation classes (Tink / Guava)
-dontwarn javax.annotation.**
-dontwarn javax.annotation.concurrent.**
-dontwarn com.google.errorprone.annotations.**
-dontwarn com.google.j2objc.annotations.**
-dontwarn org.codehaus.mojo.animal_sniffer.*

# Google Tink (SQLCipher тәуелділігі)
-dontwarn com.google.crypto.tink.**
