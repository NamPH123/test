package com.namseox.app_tcare.ui.splash

import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.namseox.app_tcare.R
import com.namseox.app_tcare.databinding.ActivitySplashBinding
import com.namseox.app_tcare.ui.language.LanguageActivity
import com.namseox.app_tcare.ui.tutorial.TutorialActivity
import com.namseox.app_tcare.utils.Const
import com.namseox.app_tcare.utils.SharedPreferenceUtils
import com.namseox.app_tcare.view.base.AbsBaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SplashActivity: AbsBaseActivity<ActivitySplashBinding>(false) {
    private val handler = Handler(Looper.myLooper()!!)
    private val runnable = Runnable {
        intent()
    }
    override fun getFragmentID(): Int = 0

    override fun getLayoutId(): Int = R.layout.activity_splash

    override fun init() {

    }

    override fun onStart() {
        super.onStart()
        handler.postDelayed(runnable, 3000)
    }

    override fun onStop() {
        super.onStop()
        handler.removeCallbacks(runnable)
    }
    fun intent() {
        if (!SharedPreferenceUtils.getInstance(applicationContext).getBooleanValue(Const.LANGUAGE)
        ) {
            startActivity(Intent(this, LanguageActivity::class.java))
        } else {
            startActivity(Intent(this, TutorialActivity::class.java))
        }
        finish()
    }
}