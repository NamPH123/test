package com.namseox.app_tcare.ui.login

import com.namseox.app_tcare.R
import com.namseox.app_tcare.databinding.ActivityLoginBinding
import com.namseox.app_tcare.view.base.AbsBaseActivity

class LoginActivity: AbsBaseActivity<ActivityLoginBinding>(false) {
    override fun getFragmentID(): Int = 0

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun init() {

    }
}