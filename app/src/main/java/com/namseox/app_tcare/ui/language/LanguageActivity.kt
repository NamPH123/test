package com.namseox.app_tcare.ui.language

import android.content.Intent
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.namseox.app_tcare.MainActivity
import com.namseox.app_tcare.R
import com.namseox.app_tcare.data.model.LanguageModel
import com.namseox.app_tcare.databinding.ActivityLanguageBinding
import com.namseox.app_tcare.ui.tutorial.TutorialActivity
import com.namseox.app_tcare.utils.Const.LANGUAGE
import com.namseox.app_tcare.utils.Const.listLanguage
import com.namseox.app_tcare.utils.SharedPreferenceUtils
import com.namseox.app_tcare.utils.SystemUtils
import com.namseox.app_tcare.utils.onClick
import com.namseox.app_tcare.view.base.AbsBaseActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class LanguageActivity: AbsBaseActivity<ActivityLanguageBinding>(false){
    lateinit var adapter: LanguageAdapter
    var codeLang: String? = null

    @Inject
    lateinit var providerSharedPreference: SharedPreferenceUtils

    override fun getFragmentID(): Int = 0

    override fun getLayoutId(): Int = R.layout.activity_language

    override fun init() {
        codeLang = providerSharedPreference.getStringValue("language")
        if (codeLang.equals("")) {
            providerSharedPreference.putStringValue("language", "vi")
            codeLang = "vi"
        }
        binding.rclLanguage.itemAnimator = null
        adapter = LanguageAdapter()
        setRecycleView()
        setClick()
    }

    private fun setClick() {
        binding.imvDone.setOnClickListener {
            onClick {
                SystemUtils.setPreLanguage(applicationContext, codeLang)
                providerSharedPreference.putStringValue("language", codeLang)
                if (SharedPreferenceUtils.getInstance(applicationContext)
                        .getBooleanValue(LANGUAGE)
                ) {
                    var intent = Intent(
                        applicationContext,
                        MainActivity::class.java
                    )
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
                    finishAffinity()
                    startActivity(intent)
                } else {
                    SharedPreferenceUtils.getInstance(applicationContext)
                        .putBooleanValue(LANGUAGE, true)
                    var intent = Intent(applicationContext, TutorialActivity::class.java)
                    startActivity(intent)
                    finish()
                }
            }
        }
    }

    private fun setRecycleView() {
//        var a = providerSharedPreference.getStringValue("language")
        var i = 0
        lateinit var x: LanguageModel
        if (!codeLang.equals("")) {
            listLanguage.forEach {
                listLanguage[i].active = false
                if (codeLang.equals(it.code)) {
                    x = listLanguage[i]
                    x.active = true
                }
                i++
            }

            listLanguage.remove(x)
            listLanguage.add(0, x)
        }
        adapter.getData(listLanguage)
        binding.rclLanguage.adapter = adapter
        val manager = GridLayoutManager(applicationContext, 1, RecyclerView.VERTICAL, false)
        binding.rclLanguage.layoutManager = manager

        adapter.onClick = {
            codeLang = listLanguage[it].code
        }
    }

}