package kr.co.kmwdev.smsreceiver

import android.annotation.SuppressLint
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.telephony.SmsMessage
import android.telephony.TelephonyManager
import android.util.Log

class SmsReceiver : BroadcastReceiver() {

    @SuppressLint("MissingPermission")
    override fun onReceive(context: Context, intent: Intent) {
        val bundle = intent.extras

        if (bundle != null) {
            val pdus = bundle["pdus"] as Array<*>
            val telephonyManager = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
            val networkType = telephonyManager.dataNetworkType
            val format = if (isCdmaNetwork(networkType)) "3gpp2" else "3gpp"

            for (pdu in pdus) {
                val smsMessage =
                    SmsMessage.createFromPdu(pdu as ByteArray, format)
                val sender = smsMessage.displayOriginatingAddress
                val messageBody = smsMessage.messageBody
                Log.d(TAG, "Received SMS from: $sender, message: $messageBody")
            }
        }
    }

    private fun isCdmaNetwork(networkType: Int): Boolean {
        return when (networkType) {
            TelephonyManager.NETWORK_TYPE_CDMA,
            TelephonyManager.NETWORK_TYPE_EVDO_0,
            TelephonyManager.NETWORK_TYPE_EVDO_A,
            TelephonyManager.NETWORK_TYPE_EVDO_B,
            TelephonyManager.NETWORK_TYPE_1xRTT -> true
            else -> false
        }
    }

    companion object {
        private const val TAG = "SmsReceiver"
    }
}