package com.namseox.app_tcare.ui.review

import android.content.Intent
import android.media.MediaPlayer
import android.media.RingtoneManager
import android.net.Uri
import com.namseox.app_tcare.R
import com.namseox.app_tcare.databinding.ActivityReviewBinding
import com.namseox.app_tcare.service.NotificationService
import com.namseox.app_tcare.utils.Const
import com.namseox.app_tcare.utils.SharedPreferenceUtils
import com.namseox.app_tcare.view.base.AbsBaseActivity
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class ActivityReview : AbsBaseActivity<ActivityReviewBinding>(false) {
    lateinit var media: MediaPlayer
    override fun getFragmentID(): Int = 0

    override fun getLayoutId(): Int = R.layout.activity_review

    override fun init() {
        val currentTime = LocalTime.now()
        val formatter = DateTimeFormatter.ofPattern("mm : HH") // Định dạng phút : Giờ
        val formattedTime = currentTime.format(formatter)
        binding.tvTime.text = formattedTime
        binding.tvAlarmNote.text =
            SharedPreferenceUtils.getInstance(this).getStringValue(Const.TITLE)
        binding.btnStop.setOnClickListener {
            finish()
        }
        val ringtoneUri: Uri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)

        // Tạo đối tượng Ringtone từ URI
        media = MediaPlayer.create(applicationContext, ringtoneUri)

        // Phát nhạc chuông
        media.isLooping = true
        media.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        val serviceIntent = Intent(this, NotificationService::class.java)
        stopService(serviceIntent)
        media.release()
    }
}