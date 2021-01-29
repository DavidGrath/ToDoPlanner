package com.example.todoplanner.framework

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

/* TODO An app setting scheduled alarms and then receiving those alarms through the Android
    framework seems kind of redundant. Look for a better alternative

 */
class TempBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        with(Intent(context!!, DayPlannerService::class.java)) {
            putExtras(intent!!)
            putExtra("ADD", 1)
            context?.startService(this)
        }
    }
}