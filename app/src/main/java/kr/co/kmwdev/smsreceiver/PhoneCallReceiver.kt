package kr.co.kmwdev.smsreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.TelephonyManager
import android.util.Log

class PhoneCallReceiver : BroadcastReceiver() {

    // Deprecated 됐지만, 가장 효율적인 방법
    override fun onReceive(context: Context, intent: Intent) {
        val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        telephonyManager.listen(object : android.telephony.PhoneStateListener() {
            override fun onCallStateChanged(state: Int, incomingNumber: String?) {
                when (state) {
                    TelephonyManager.CALL_STATE_RINGING -> {
                        Log.d(TAG, "Incoming call ringing from: $incomingNumber")
                    }
                    TelephonyManager.CALL_STATE_OFFHOOK -> {
                        Log.d(TAG, "Call answered")
                    }
                    TelephonyManager.CALL_STATE_IDLE -> {
                        Log.d(TAG, "Call ended or idle")
                    }
                }
            }
        }, android.telephony.PhoneStateListener.LISTEN_CALL_STATE)
    }

    companion object {
        private const val TAG = "PhoneCallReceiver"
    }
}
