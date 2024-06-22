package com.namseox.app_tcare.ui.resetpass

import androidx.activity.viewModels
import com.namseox.app_tcare.R
import com.namseox.app_tcare.data.model.ChangePass
import com.namseox.app_tcare.databinding.ActivityResetPassBinding
import com.namseox.app_tcare.ui.register.RegisterViewModel
import com.namseox.app_tcare.utils.Const.token
import com.namseox.app_tcare.utils.showToast
import com.namseox.app_tcare.view.base.AbsBaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ResetPassActivity: AbsBaseActivity<ActivityResetPassBinding>(false) {
    val viewModel: RegisterViewModel by viewModels()
    override fun getFragmentID(): Int = 0

    override fun getLayoutId(): Int = R.layout.activity_reset_pass

    override fun init() {
            binding.btnRegister.setOnClickListener {
                viewModel.changePass(token, ChangePass(binding.edtEmail.text.toString(),binding.edtPass.text.toString(),binding.edtPass2.text.toString()))
                showToast(this,R.string.success)
                finish()
            }
    }
}