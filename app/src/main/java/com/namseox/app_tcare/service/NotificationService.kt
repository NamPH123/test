package com.namseox.app_tcare.service

import android.app.Service
import android.content.ContentValues.TAG
import android.content.Intent
import android.os.IBinder
import android.util.Log
import com.namseox.app_tcare.ui.review.ActivityReview
import com.namseox.app_tcare.utils.Const
import com.namseox.app_tcare.utils.Const.NOTIFICATION
import com.namseox.app_tcare.utils.SharedPreferenceUtils
import com.namseox.app_tcare.utils.putParcelableExtra

class NotificationService : Service(){
    override fun onBind(intent: Intent?): IBinder? {
        return null
    }
    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {


        return START_STICKY;
    }

    override fun onCreate() {
        super.onCreate()
        Log.d(TAG, "onCreate:00000 ")
        val intent = Intent(this, ActivityReview::class.java)
        var a = SharedPreferenceUtils.getInstance(this).getBooleanValue(NOTIFICATION)
        if(!a){
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }
//        if (checkOpenCall){
//            if(a!=null){
//                sendBroadcast(Intent("destroy"))
//                intent.putParcelableExtra("data", a)
//
//
//            }
//        }else{
//            if(a!=null){
//                intent.putParcelableExtra("data", a)
//                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
//                startActivity(intent)
//            }
//        }
    }
}