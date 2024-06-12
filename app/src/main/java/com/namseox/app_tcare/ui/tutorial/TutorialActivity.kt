package com.namseox.app_tcare.ui.tutorial

import android.content.Intent
import android.text.TextUtils
import android.view.View
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.namseox.app_tcare.MainActivity
import com.namseox.app_tcare.R
import com.namseox.app_tcare.data.model.TutorialModel
import com.namseox.app_tcare.databinding.ActivityTutorialBinding
import com.namseox.app_tcare.ui.login.LoginActivity
import com.namseox.app_tcare.utils.Const
import com.namseox.app_tcare.utils.SharedPreferenceUtils
import com.namseox.app_tcare.utils.changeText
import com.namseox.app_tcare.utils.onClick
import com.namseox.app_tcare.view.base.AbsBaseActivity
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class TutorialActivity: AbsBaseActivity<ActivityTutorialBinding>(false) {
    @Inject
    lateinit var sharedPreferenceUtils: SharedPreferenceUtils
    var listFragment = 3
    private var viewPagerAdapter: ViewPagerAdapter? = null

    override fun getFragmentID(): Int = 0

    override fun getLayoutId(): Int = R.layout.activity_tutorial

    override fun init() {
        val space = " "
        var data = arrayListOf<TutorialModel>(
            TutorialModel(
                R.drawable.photo_intro1,
                getString(R.string.t1)
                ),
            TutorialModel(
                R.drawable.photo_intro2,
                getString(R.string.t2)
            ),
            TutorialModel(
                R.drawable.photo_intro3,
                TextUtils.concat(
                    changeText(this, getString(R.string.become_the), R.color.white, R.font.grandstander_bold),
                    space,
                    changeText(this, getString(R.string.ultimate_prankster), R.color._ffe500, R.font.grandstander_bold),
                )
            )
        )
        viewPagerAdapter = ViewPagerAdapter()
        viewPagerAdapter!!.getData(data)
        val compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(100))
        compositePageTransformer.addTransformer { page: View, position: Float ->
            val r = 1 - Math.abs(position)
            page.scaleY = 0.8f + r * 0.2f
            val absPosition = Math.abs(position)
            page.alpha = 1.0f - (1.0f - 0.3f) * absPosition
        }

        binding.pageIndicatorView.count = listFragment
        binding.viewPager.setPageTransformer(compositePageTransformer)
        bindViewModel()

    }

    private fun bindViewModel() {
        binding.viewPager.adapter = viewPagerAdapter
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.pageIndicatorView.selection = position
            }
        })
        binding.btnNext.setOnClickListener {

            if (binding.viewPager.currentItem < listFragment - 1) {
                binding.viewPager.setCurrentItem(binding.viewPager.currentItem + 1)
            }else{
                onClick {
                    if (!sharedPreferenceUtils.getBooleanValue(Const.LOGIN) ) {
                        val intent = Intent(this@TutorialActivity, LoginActivity::class.java)
                        startActivity(intent)
                        finish()
                    } else {
                            val intent = Intent(this@TutorialActivity, MainActivity::class.java)
                            startActivity(intent)
                        finish()
                    }
                }
            }
        }
    }
}