package com.namseox.app_tcare.view.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.namseox.app_tcare.utils.SystemUtils
import com.namseox.app_tcare.utils.showSystemUI

abstract class AbsBaseActivity<V : ViewDataBinding>(var fragment : Boolean) : AppCompatActivity() {
    lateinit var binding: V
    lateinit var navHostFragment: NavHostFragment
    lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        SystemUtils.setLocale(this)
        binding = DataBindingUtil.setContentView(this, getLayoutId())
        if(fragment){
            navHostFragment =
                supportFragmentManager.findFragmentById(getFragmentID()) as NavHostFragment
            navController = navHostFragment.navController
        }
        init()
    }
    override fun onResume() {
        super.onResume()
        showSystemUI(false)
    }

    abstract fun getFragmentID(): Int
    abstract fun getLayoutId(): Int
    abstract fun init()

}