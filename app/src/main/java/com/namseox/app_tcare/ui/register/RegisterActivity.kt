package com.namseox.app_tcare.ui.register

import androidx.activity.viewModels
import com.namseox.app_tcare.R
import com.namseox.app_tcare.data.api.reponse.DataResponse
import com.namseox.app_tcare.data.api.reponse.LoadingStatus
import com.namseox.app_tcare.data.model.CallError
import com.namseox.app_tcare.data.model.RegisterResponse
import com.namseox.app_tcare.data.model.UserLoginModel
import com.namseox.app_tcare.data.model.UserLoginModelRe
import com.namseox.app_tcare.databinding.ActivityRegisterBinding
import com.namseox.app_tcare.utils.SharedPreferenceUtils
import com.namseox.app_tcare.utils.showToast
import com.namseox.app_tcare.view.base.AbsBaseActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RegisterActivity : AbsBaseActivity<ActivityRegisterBinding>(false) {
    @Inject
    lateinit var sharedPreferenceUtils: SharedPreferenceUtils
    var email = ""
    var pass = ""
    var name = ""
    val viewModel: RegisterViewModel by viewModels()

    override fun getFragmentID(): Int = 0

    override fun getLayoutId(): Int = R.layout.activity_register

    override fun init() {
        binding.imvback.setOnClickListener {
            finish()
        }
        binding.btnRegister.setOnClickListener {
            email = binding.edtEmail.text.toString()
            pass = binding.edtPass.text.toString()
            name = binding.edtName.text.toString()
            if (pass.equals(binding.edtPass2.text.toString())) {
                viewModel.register(UserLoginModelRe(email, pass,name))

            } else {
                showToast(this@RegisterActivity, R.string.error_pass)
            }
        }
        viewModel.data.observe(this){
            it.let {
                if(it.loadingStatus==LoadingStatus.Loading){
                    //
                }else{
                    if(it.loadingStatus==LoadingStatus.Success){
                            showToast(this,R.string.success)
                            finish()
                    }else{
                        showToast(this,R.string.error)
                    }
                }
            }
        }
    }
}