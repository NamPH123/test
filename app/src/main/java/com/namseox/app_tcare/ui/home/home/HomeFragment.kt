package com.namseox.app_tcare.ui.home.home

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.view.View
import androidx.core.app.AlarmManagerCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.namseox.app_tcare.R
import com.namseox.app_tcare.data.model.AddBooking
import com.namseox.app_tcare.data.model.ClinicModel
import com.namseox.app_tcare.data.model.DoctorModel
import com.namseox.app_tcare.data.model.PackageModel
import com.namseox.app_tcare.data.model.TutorialModel
import com.namseox.app_tcare.databinding.FragmentHomeBinding
import com.namseox.app_tcare.service.AlarmReceiver
import com.namseox.app_tcare.ui.dialog.DialogCalender
import com.namseox.app_tcare.ui.home.MainActivity
import com.namseox.app_tcare.ui.home.MainViewModel
import com.namseox.app_tcare.utils.Const
import com.namseox.app_tcare.utils.Const.arrPackage
import com.namseox.app_tcare.utils.Const.token
import com.namseox.app_tcare.utils.Const.user
import com.namseox.app_tcare.utils.SharedPreferenceUtils
import com.namseox.app_tcare.utils.showSystemUI
import com.namseox.app_tcare.view.base.AbsBaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar
import javax.inject.Inject
import kotlin.random.Random

@AndroidEntryPoint
class HomeFragment : AbsBaseFragment<FragmentHomeBinding, MainActivity>(R.layout.fragment_home) {
    @Inject
    lateinit var sharedPreferenceUtils: SharedPreferenceUtils
    val viewModel: MainViewModel by viewModels()
    var listFragment = 3
    lateinit var adapterViewPager: ViewPagerAdapter
    lateinit var adapterDoctor: DoctorAdapter
    lateinit var adapterClinic: ClinicAdapter
    lateinit var adapterPackage: PackageAdapter
    var arrDoctor = arrayListOf(
        DoctorModel(
            "Hoàng Văn Cầu",
            "https://suckhoedoisong.qltns.mediacdn.vn/thumb_w/640/324455921873985536/2023/12/25/1-17035025379211648167770.png",
            "Hà Đông, Hà Nội"
        ),
        DoctorModel(
            "Nguyễn Minh Tiến",
            "https://myduchospital.vn/vnt_upload/doctors/03_2022/thumbs/(270x360)__BS_Nguyen_Thanh_Nam.jpg",
            "Hà Đông, Hà Nội"
        ),
        DoctorModel(
            "Trần Văn Cường",
            "https://hthaostudio.com/wp-content/uploads/2022/03/Anh-bac-si-nam-7-min.jpg.webp",
            "Hà Đông, Hà Nội"
        ),
    )
    var arrClinic = arrayListOf(
        ClinicModel(
            "Phòng khám đa khoa Hồng Phát",
            "0123456789",
            "Hà Nội",
            "https://cdn.hellobacsi.com/wp-content/uploads/2022/10/phong-kham-da-khoa-hong-phat.jpg"
        ),
        ClinicModel(
            "Đa khoa quốc tế Hà Nội",
            "0123456789",
            "Hà Nội",
            "https://cdn.hellobacsi.com/wp-content/uploads/2022/10/phong-kham-da-khoa-quoc-te-ha-noi.jpg"
        ),
        ClinicModel(
            "Phòng khám đa khoa Thu Cúc",
            "0123456789",
            "Hà Nội",
            "https://cdn.hellobacsi.com/wp-content/uploads/2022/10/phong-kham-da-khoa-quoc-te-thu-cuc.jpg"
        ),
    )

    var data = arrayListOf<TutorialModel>()
    override fun initView() {
        adapterViewPager = ViewPagerAdapter()
        data = arrayListOf<TutorialModel>(
            TutorialModel(
                R.drawable.photo_home1,
                getString(R.string.gi_m_gi_g_i_kh_m_t_ng_quan_tr_n_g_i_t_i_15_ngay_trong_ng_y_h_m_nay)
            ),
            TutorialModel(
                R.drawable.photo_home2,
                getString(R.string.giam_gia)
            ),
            TutorialModel(
                R.drawable.photo_home3,
                getString(R.string.tin_tuc_moi)
            )
        )
    }

    override fun setView() {
        adapterViewPager.getData(data)
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
        setRecycleview()
    }

    private fun setRecycleview() {
        adapterClinic = ClinicAdapter()
        adapterDoctor = DoctorAdapter()
        adapterPackage = PackageAdapter()

        binding.rcv1.adapter = adapterDoctor
        binding.rcv2.adapter = adapterClinic
        binding.rcv3.adapter = adapterPackage

        var layoutManager1 = GridLayoutManager(requireContext(), 1, RecyclerView.HORIZONTAL, false)
        var layoutManager2 = GridLayoutManager(requireContext(), 1, RecyclerView.HORIZONTAL, false)
        var layoutManager3 = GridLayoutManager(requireContext(), 1, RecyclerView.HORIZONTAL, false)
        binding.rcv1.layoutManager = layoutManager1
        binding.rcv2.layoutManager = layoutManager2
        binding.rcv3.layoutManager = layoutManager3
        adapterPackage.submitList(arrPackage)
        adapterDoctor.submitList(arrDoctor)
        adapterClinic.submitList(arrClinic)
    }

    private fun bindViewModel() {
        binding.viewPager.adapter = adapterViewPager
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                binding.pageIndicatorView.selection = position
            }
        })
    }

    override fun setClick() {
        adapterPackage.onclick = { pos ->
            var dialog = DialogCalender(mActivity)
            dialog.init(object : DialogCalender.OnPress {
                override fun ok() {
                    var time = sharedPreferenceUtils.getStringValue(
                        Const.CALANDERDIALOG,
                    )
                    val parts = time!!.split("/")

                    mActivity.apply {
                        val alarmManager =
                            mActivity.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                        val alarmIntent = Intent(this, AlarmReceiver::class.java)
                        val calendar = Calendar.getInstance()
                        calendar.apply {
                            set(Calendar.YEAR, parts[4].toInt())
                            set(Calendar.MONTH, parts[3].toInt() - 1)
                            set(Calendar.DAY_OF_MONTH, parts[2].toInt())
                            set(Calendar.HOUR_OF_DAY, parts[1].toInt())
                            set(Calendar.MINUTE, parts[0].toInt())
                            set(Calendar.SECOND, 0)
                        }
                        var triggerTime = calendar.timeInMillis
                        val pendingIntent = PendingIntent.getBroadcast(
                            this,
                            pos, //duy nhat
                            alarmIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
                        )
                        alarmManager.cancel(pendingIntent)
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                            AlarmManagerCompat.setExact(
                                alarmManager,
                                AlarmManager.RTC_WAKEUP,
                                triggerTime!!,
                                pendingIntent
                            )
                        } else {
                            alarmManager.setExactAndAllowWhileIdle(
                                AlarmManager.RTC_WAKEUP,
                                triggerTime!!,
                                pendingIntent
                            )
                        }
                        viewModel.booking(
                            token,
                            AddBooking(
                                serviceIds = when (Random.nextInt(0, 4)) {
                                    0 -> {
                                        intArrayOf(32)
                                    }
                                    1 -> {
                                        intArrayOf(33)
                                    }
                                    2->{intArrayOf(34)}
                                    else -> {
                                        intArrayOf(35)
                                    }
                                },
                                fullName = user.fullName,
                                userId = 0,
                                address = user.address,
                                phoneNumber = user.phoneNumber,
                                startTime = "${parts[1]}:${parts[0]}",
                                bookingDate = "${parts[2]}-${parts[3]}-${parts[4]}"
                            )
                        )
                    }
                }

                override fun cancel() {

                }

            })
            dialog.setOnDismissListener {
                mActivity.showSystemUI(false)
            }
            dialog.show()
        }
    }
}