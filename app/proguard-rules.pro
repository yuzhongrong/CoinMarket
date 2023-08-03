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
-keep class com.google.android.material.** {*;}
-keep class androidx.** {*;}
-keep public class * extends androidx.**
-keep interface androidx.** {*;}
-keep public class * extends com.kunminx.architecture.**
#databinding
-dontwarn android.databinding.**
-keep class android.databinding.** { *; }

-dontwarn com.google.android.material.**
-dontnote com.google.android.material.**
-dontwarn androidx.**
-optimizationpasses 5 # 指定代码的压缩级别
-dontusemixedcaseclassnames  # 是否使用大小写混合
-dontskipnonpubliclibraryclasses # 是否混淆第三方jar
-dontpreverify # 混淆时是否做预校验
-verbose  # 混淆时是否记录日志
-optimizations !code/simplification/arithmetic,!field/*,!class/merging/* # 混淆时所采用的算法
-ignorewarnings  # 忽略警告
-keepattributes Signature# 保留泛型与反射
-keepattributes *Annotation* # 表示对注解中的参数进行保留
-keepattributes *JavascriptInterface* # 保留WebView与JavaScript交互代码

-keep public class * extends android.app.Activity  # 保持哪些类不被混淆
-keep public class * extends android.app.Application  # 保持哪些类不被混淆
-keep public class * extends android.app.Service  # 保持哪些类不被混淆
-keep public class * extends android.content.BroadcastReceiver # 保持哪些类不被混淆
-keep public class * extends android.content.ContentProvider # 保持哪些类不被混淆
-keep public class * extends android.app.backup.BackupAgentHelper # 保持哪些类不被混淆
-keep public class * extends android.preference.Preference # 保持哪些类不被混淆
#-keep public class com.android.vending.licensing.ILicensingService # 保持哪些类不被混淆

# 保持 native 方法不被混淆
-keepclasseswithmembernames class * {
    native <methods>;
}

# 保持自定义控件类不被混淆
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet);
}

 # 表示示不混淆任何一个View中的setXxx()和getXxx()方法，
 # 因为属性动画需要有相应的setter和getter的方法实现，混淆了就无法工作了
-keepclassmembers public class * extends android.view.View {
   void set*(***);
   *** get*();
}

# 保持自定义控件类不被混淆
-keepclasseswithmembers class * {
    public <init>(android.content.Context, android.util.AttributeSet, int);
}
# 表示不混淆Activity中参数是View的方法。因为点击事件有这样一种写法，
# 在XML中配置android:onClick="buttonClick"属性，
# 当用户点击该按钮时就会调用Activity中的buttonClick(View  view)方法，
# 如果这个方法被混淆的话就找不到了
-keepclassmembers class * extends android.app.Activity {
   public void *(android.view.View);
}

 # 保持枚举 enum 类不被混淆
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}

  # 保持 Parcelable 不被混淆
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

 # 表示不混淆R文件中的所有静态字段，我们都知道R文件是通过字段来记录每个资源的id的，
 # 字段名要是被混淆了，id也就找不着了
-keepclassmembers class **.R$* {
    public static <fields>;
}

# 保持自己定义的类不被混淆
#-keep class MyClass;

# tokenpocket sdk
-dontwarn com.tokenpocket.opensdk.**
-keep class com.tokenpocket.opensdk.**{*;}
-keep interface com.tokenpocket.opensdk.**{*;}

-keep class com.wang.avi.** { *; }
-keep class com.wang.avi.indicators.** { *; }
-keep class okhttp3.** { *; }
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Service

