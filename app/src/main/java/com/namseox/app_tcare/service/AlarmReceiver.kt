package com.namseox.app_tcare.service

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        var i = Intent(
            context,
            NotificationService::class.java
        )
        context!!.startService(i)
    }
}