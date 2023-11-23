# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile

# Please add these rules to your existing keep rules in order to suppress warnings.
# This is generated automatically by the Android Gradle plugin.
-dontwarn org.bouncycastle.jsse.BCSSLParameters
-dontwarn org.bouncycastle.jsse.BCSSLSocket
-dontwarn org.bouncycastle.jsse.provider.BouncyCastleJsseProvider
-dontwarn org.conscrypt.Conscrypt$Version
-dontwarn org.conscrypt.Conscrypt
-dontwarn org.conscrypt.ConscryptHostnameVerifier
-dontwarn org.openjsse.javax.net.ssl.SSLParameters
-dontwarn org.openjsse.javax.net.ssl.SSLSocket
-dontwarn org.openjsse.net.ssl.OpenJSSE


# Keep native methods
    -keepclassmembers class * {
        native <methods>;
    }

    -dontwarn okio.**
    -dontwarn com.squareup.okhttp.**
    -dontwarn javax.annotation.**
    -dontwarn com.android.volley.toolbox.**

    -ignorewarnings
    -keep class * {
        public private *;
    }
# Keep native methods

# Google
    -dontwarn com.google.android.material.**
    -keep class com.google.android.material.** { *; }

    -dontwarn androidx.**
    -keep class androidx.** { *; }
    -keep interface androidx.** { *; }
# Google

# Retrofit 2.X
    ## https://square.github.io/retrofit/ ##
    -dontwarn retrofit2.**
    -keep class retrofit2.** { *; }
    -keep class okhttp3.** { *; }
    -keepattributes Signature
    -keepattributes Exceptions

    -keepclasseswithmembers class * {
        @retrofit2.http.* <methods>;
    }

# Glide
    -keep public class * implements com.bumptech.glide.module.GlideModule
    -keep public class * extends com.bumptech.glide.AppGlideModule
    -keep public enum com.bumptech.glide.load.resource.bitmap.ImageHeaderParser$** {
      **[] $VALUES;
      public *;
    }

# Firebase Crashlytics
    -keepattributes SourceFile,LineNumberTable
