
-keep class com.haha.fastproject.push.PushModuleInit

# XPush的混淆
-keep class * extends com.xuexiang.xpush.core.IPushClient{*;}
-keep class * extends com.xuexiang.xpush.core.receiver.IPushReceiver{*;}