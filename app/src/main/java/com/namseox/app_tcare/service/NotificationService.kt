package com.namseox.app_tcare.service

import android.app.Service
import android.content.Intent
import android.os.IBinder
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
        val intent = Intent(this, ActivityReview::class.java)
        var a = SharedPreferenceUtils.getInstance(this).getObjModel<CallPhoneModel>()
        if (checkOpenCall){
            if(a!=null){
                sendBroadcast(Intent("destroy"))
                intent.putParcelableExtra("data", a)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        }else{
            if(a!=null){
                intent.putParcelableExtra("data", a)
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                startActivity(intent)
            }
        }
    }
}