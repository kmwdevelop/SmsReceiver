package kr.co.kmwdev.smsreceiver

import android.service.notification.NotificationListenerService
import android.service.notification.StatusBarNotification
import android.util.Log


class KakaoNotificationListener : NotificationListenerService() {

    override fun onNotificationPosted(sbn: StatusBarNotification) {
        // 알림이 게시될 때 호출됩니다.
        if (sbn.packageName == "com.kakao.talk") {
            val notification = sbn.notification
            val extras = notification.extras
            val title = extras.getString("android.title")
            val text = extras.getCharSequence("android.text").toString()

            Log.d(TAG, "KakaoTalk message - Title: $title, Text: $text")

            // 메시지를 처리하는 추가 로직을 여기에 추가할 수 있습니다.
        }
    }

    override fun onNotificationRemoved(sbn: StatusBarNotification) {
        // 알림이 제거될 때 호출됩니다.
        Log.d(TAG, "Notification removed: ${sbn.packageName}")
    }

    companion object {
        private const val TAG = "KakaoNotificationListener"
    }
}
