
#--------------------------------- 不混淆base包 ------------------------------------
-keep class com.haha.fastproject.base.base.** { *; }

#----------------------------------- ARoute -----------------------------------------
-keep public class com.alibaba.android.arouter.routes.**{*;}
-keep public class com.alibaba.android.arouter.facade.**{*;}
-keep class * implements com.alibaba.android.arouter.facade.template.ISyringe{*;}

# 如果使用了 byType 的方式获取 Service，需添加下面规则，保护接口
-keep interface * implements com.alibaba.android.arouter.facade.template.IProvider

# 如果使用了 单类注入，即不定义接口实现 IProvider，需添加下面规则，保护实现
# -keep class * implements com.alibaba.android.arouter.facade.template.IProvider
#----------------------------------------------------------------------------

#---------------------------------agentweb------------------------------------
-keep class com.just.agentweb.** {
    *;
}
-dontwarn com.just.agentweb.**


#---------------------------------PictureSelector------------------------------------
#PictureSelector 2.0
-keep class com.luck.picture.lib.** { *; }

#Ucrop
-dontwarn com.yalantis.ucrop**
-keep class com.yalantis.ucrop** { *; }
-keep interface com.yalantis.ucrop** { *; }

#Okio
-dontwarn org.codehaus.mojo.animal_sniffer.*



#---------------------------------公共契约类------------------------------------
#保持此包和子包类名和里面内容不被混淆
-keep class com.haha.fastproject.base.contract.*{*;}

#---------------------------------自定义控件包------------------------------------
-keep class com.haha.fastproject.base.widget.** { *; }


#---------------------------------GreenDao------------------------------------
-keepclassmembers class * extends org.greenrobot.greendao.AbstractDao {
public static java.lang.String TABLENAME;
}
-keep class **$Properties { *; }

# If you DO use SQLCipher:
-keep class org.greenrobot.greendao.database.SqlCipherEncryptedHelper { *; }

# If you do NOT use SQLCipher:
-dontwarn net.sqlcipher.database.**
# If you do NOT use RxJava:
-dontwarn rx.**


