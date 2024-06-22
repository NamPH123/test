package com.namseox.app_tcare.ui.dialog

import android.app.Activity
import com.namseox.app_tcare.R
import com.namseox.app_tcare.databinding.DialogTextBinding
import com.namseox.app_tcare.utils.Const
import com.namseox.app_tcare.utils.SharedPreferenceUtils
import com.namseox.app_tcare.view.base.AbsDialog

class DialogText(context: Activity) : AbsDialog<DialogTextBinding>(context, false) {
    private lateinit var onPress: OnPresss
    override fun getContentView(): Int = R.layout.dialog_text
    override fun initView() {

    }

    override fun bindView() {
       binding.btnOk.setOnClickListener {
           SharedPreferenceUtils.getInstance(context).putStringValue(Const.TITLE,binding.edtNameFolder.text.toString())
           onPress.ok()
           dismiss()

       }
    }

    interface OnPresss {
        fun ok()
        fun cancel()

    }

    fun init(onPresss: OnPresss) {
        this.onPress = onPresss!!
    }
}