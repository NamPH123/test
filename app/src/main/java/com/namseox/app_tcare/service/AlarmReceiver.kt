package com.namseox.app_tcare.service

import android.content.BroadcastReceiver
import android.content.ContentValues
import android.content.Context
import android.content.Intent
import android.util.Log

class AlarmReceiver: BroadcastReceiver() {
    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(ContentValues.TAG, "onReceive:........ ")
        var i = Intent(
            context,
            NotificationService::class.java
        )
        context!!.startService(i)
    }
}