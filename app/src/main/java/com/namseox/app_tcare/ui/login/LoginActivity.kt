package com.namseox.app_tcare.ui.login

import android.annotation.SuppressLint
import android.content.ContentValues.TAG
import android.content.Intent
import android.util.Log
import androidx.activity.viewModels
import com.namseox.app_tcare.R
import com.namseox.app_tcare.data.api.reponse.DataResponse
import com.namseox.app_tcare.data.api.reponse.LoadingStatus
import com.namseox.app_tcare.data.model.CallError
import com.namseox.app_tcare.data.model.RegisterResponse
import com.namseox.app_tcare.data.model.UserLoginModel
import com.namseox.app_tcare.databinding.ActivityLoginBinding
import com.namseox.app_tcare.ui.home.MainActivity
import com.namseox.app_tcare.ui.register.RegisterActivity
import com.namseox.app_tcare.ui.register.RegisterViewModel
import com.namseox.app_tcare.utils.Const.token
import com.namseox.app_tcare.utils.Const.user
import com.namseox.app_tcare.utils.showToast
import com.namseox.app_tcare.view.base.AbsBaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginActivity: AbsBaseActivity<ActivityLoginBinding>(false) {
    val viewModel: RegisterViewModel by viewModels()
    override fun getFragmentID(): Int = 0

    override fun getLayoutId(): Int = R.layout.activity_login

    override fun init() {
        setClick()
    }

    @SuppressLint("SuspiciousIndentation")
    private fun setClick() {
        binding.apply {
            btnLogin.setOnClickListener {
//                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
//                finish()
                viewModel.login(UserLoginModel(binding.edtEmail.text.toString(), binding.edtPass.text.toString()))
            }
            tvRegister.setOnClickListener {
                startActivity(Intent(this@LoginActivity, RegisterActivity::class.java))
//                finish()
            }
        }
        viewModel.data.observe(this){
            it.let {
                if(it.loadingStatus== LoadingStatus.Loading){

                }else{
                    if(it.loadingStatus== LoadingStatus.Success){
                        var body = (it as DataResponse.DataSuccess).body

                                user= (body.body() as RegisterResponse).result.user
                        token = "Bearer "+(body.body() as RegisterResponse).result.accessToken
                                startActivity(Intent(this@LoginActivity, MainActivity::class.java))
                                finish()


                    }else{
                        showToast(this,R.string.error_login)
                    }
                }
            }
        }
    }
}