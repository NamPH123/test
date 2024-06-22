package com.namseox.app_tcare.ui.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.ui.setupWithNavController
import com.bumptech.glide.Glide
import com.namseox.app_tcare.R
import com.namseox.app_tcare.databinding.ActivityMainBinding
import com.namseox.app_tcare.utils.Const
import com.namseox.app_tcare.utils.SharedPreferenceUtils
import com.namseox.app_tcare.view.base.AbsBaseActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AbsBaseActivity<ActivityMainBinding>(true) {
    @Inject
    lateinit var sharedPreferenceUtils: SharedPreferenceUtils
    var checkNotifi = true
    override fun getFragmentID(): Int = R.id.nav_main

    override fun getLayoutId(): Int = R.layout.activity_main

    override fun init() {
        checkNotifi = sharedPreferenceUtils.getBooleanValue(Const.NOTIFICATION)

        Glide.with(this).load(
            if(!checkNotifi){
R.drawable.ic_bell
            }else{
                R.drawable.ic_non_beo
            }
        ).into(binding.imvNotifi)
        binding.bottomNav.setupWithNavController(navController)
        binding.imvNotifi.setOnClickListener {
            checkNotifi = !checkNotifi
            sharedPreferenceUtils.putBooleanValue(Const.NOTIFICATION,checkNotifi)
            Glide.with(this).load(
                if(!checkNotifi){
                    R.drawable.ic_bell
                }else{
                    R.drawable.ic_non_beo
                }
            ).into(binding.imvNotifi)
        }
    }

}
