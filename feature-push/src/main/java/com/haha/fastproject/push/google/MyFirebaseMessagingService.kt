package com.haha.fastproject.push.google

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.haha.fastproject.push.R
import com.haha.fastproject.push.xpush.FCMPushClient
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.xuexiang.xpush.XPush
import com.xuexiang.xpush.entity.XPushMsg
import com.xuexiang.xpush.util.PushUtils
import me.goldze.mvvmhabit.utils.KLog

/**
 * @author zhe.chen
 * @date 4/30/21 10:01
 * Des:Google FCM推送 应用在前台收到推送处理
 */
class MyFirebaseMessagingService : FirebaseMessagingService() {

    private val TAG = "MyFirebaseMessagingService"

    override fun onCreate() {
        super.onCreate()
        KLog.e(TAG, "FCM消息服务已开启")
    }

    /**
     * 获取到Google的推送Token
     */
    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        KLog.e(TAG, "onNewToken--->${p0}")
        PushUtils.savePushToken(FCMPushClient.MIPUSH_PLATFORM_NAME, p0)
        sendRegistrationToServer(p0)
    }

    /**
     * 应用在前台接收到消息时回调
     */
    override fun onMessageReceived(remoteMessage: RemoteMessage) {
        super.onMessageReceived(remoteMessage)
        val notification = remoteMessage.notification
        KLog.e(TAG, "收到消息--->${remoteMessage.data}")
        KLog.e(TAG, "APP处于前台消息标题" + remoteMessage.getNotification()?.getTitle())
        KLog.e(TAG, "APP处于前台消息内容" + remoteMessage.getNotification()?.getBody())
        KLog.e(TAG, "Data消息（为空）" + remoteMessage.getData())
        KLog.e(TAG, "服务器" + remoteMessage.getFrom())

        remoteMessage.apply {
            notification?.run {
                val xPushMsg = XPushMsg(-1, title, body, "", "", this@apply.getData())
                XPush.transmitMessage(this@MyFirebaseMessagingService, xPushMsg)
            }
        }
        remoteMessage.notification?.run {
            if (this.body != null) {
                sendNotification(getTitle(), body)
            } else {
                sendNotification(remoteMessage.getData().get("title"), remoteMessage.getData()
                    .get("body"))
            }
        }
    }

    /**
     * 应用在前台时收到推送，显示通知
     */
    private fun sendNotification(messageTitle: String?, messageBody: String?) {
        if (messageTitle.isNullOrBlank() || messageBody.isNullOrBlank()) return
        val channelId = getString(R.string.res_push_notification_channel_id)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setTicker(messageTitle)//标题
            .setSmallIcon(R.drawable.res_icon_notification)//你的推送栏图标
            .setContentTitle("notification")
            .setContentText(messageBody)//内容
            .setAutoCancel(true)
            .setSound(defaultSoundUri)

        try {
            val toActivityClass = Class.forName("com.haha.fastproject.main.home.MainActivity")
            val intent = Intent(this, toActivityClass)
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            val pendingIntent =
                PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_ONE_SHOT)
            notificationBuilder.setContentIntent(pendingIntent)
        } catch (e: ClassNotFoundException) {
            KLog.e(e.message)
        }

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        //判断版本
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel =
                NotificationChannel(channelId, "FCM推送通知", NotificationManager.IMPORTANCE_HIGH);notificationManager.createNotificationChannel(channel);
        }
        //这里如果需要的话填写你自己项目的，可以在控制台找到，强转成int类型
        notificationManager.notify(0, notificationBuilder.build())
    }


    private fun sendRegistrationToServer(p0: String) {

    }
}