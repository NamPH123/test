package com.namseox.app_tcare.ui

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.AlarmManagerCompat
import com.namseox.app_tcare.R
import com.namseox.app_tcare.databinding.ActivitySplashBinding
import com.namseox.app_tcare.service.AlarmReceiver
import com.namseox.app_tcare.view.base.AbsBaseActivity
import java.util.Calendar

class NotificationActivity : AbsBaseActivity<ActivitySplashBinding>(false) {
    var timeCalander = ""
    override fun getFragmentID(): Int = 0

    override fun getLayoutId(): Int = R.layout.activity_manage_notification

    override fun init() {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val alarmIntent = Intent(this, AlarmReceiver::class.java)
        val calendar = Calendar.getInstance()
        calendar.apply {
            set(Calendar.YEAR, nam.toInt())
            set(Calendar.MONTH, thang.toInt() - 1)
            set(Calendar.DAY_OF_MONTH, ngay.toInt())
            set(Calendar.HOUR_OF_DAY, gio.toInt())
            set(Calendar.MINUTE, phut.toInt())
            set(Calendar.SECOND, 0)
        }
        var  triggerTime = calendar.timeInMillis
        val pendingIntent = PendingIntent.getBroadcast(
            this,
            id, //duy nhat
            alarmIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
        )
        alarmManager.cancel(pendingIntent)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            AlarmManagerCompat.setExact(
                alarmManager,
                AlarmManager.RTC_WAKEUP,
                triggerTime!!,
                pendingIntent
            )
        } else {
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                triggerTime!!,
                pendingIntent
            )
        }
    }

}