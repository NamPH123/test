package com.namseox.app_tcare.ui.home.calander

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.appcompat.widget.SearchView
import androidx.core.app.AlarmManagerCompat
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.namseox.app_tcare.R
import com.namseox.app_tcare.data.api.reponse.DataResponse
import com.namseox.app_tcare.data.api.reponse.LoadingStatus
import com.namseox.app_tcare.data.model.AddBooking
import com.namseox.app_tcare.data.model.BookingModel
import com.namseox.app_tcare.data.model.ItemNotifi
import com.namseox.app_tcare.data.model.PackageModel
import com.namseox.app_tcare.databinding.FragmentCalenderBinding
import com.namseox.app_tcare.service.AlarmReceiver
import com.namseox.app_tcare.ui.dialog.DialogCalender
import com.namseox.app_tcare.ui.home.MainActivity
import com.namseox.app_tcare.ui.home.MainViewModel
import com.namseox.app_tcare.ui.home.home.PackageAdapter
import com.namseox.app_tcare.utils.Const
import com.namseox.app_tcare.utils.Const.arrPackage
import com.namseox.app_tcare.utils.SharedPreferenceUtils
import com.namseox.app_tcare.utils.showSystemUI
import com.namseox.app_tcare.view.base.AbsBaseFragment
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar
import javax.inject.Inject
import kotlin.random.Random

@AndroidEntryPoint
class CalanderFragment :
    AbsBaseFragment<FragmentCalenderBinding, MainActivity>(R.layout.fragment_calender) {
    var currentTimeMillis = 0L
    lateinit var parts: List<String>

    @Inject
    lateinit var sharedPreferenceUtils: SharedPreferenceUtils
    val viewModel: MainViewModel by viewModels()
    lateinit var adapterPackage: PackageAdapter


    override fun initView() {
        adapterPackage = PackageAdapter()
        binding.rcv.adapter = adapterPackage
        var layoutManager3 = GridLayoutManager(requireContext(), 1, RecyclerView.VERTICAL, false)
        binding.rcv.layoutManager = layoutManager3
        adapterPackage.submitList(arrPackage)
    }

    override fun setView() {
        viewModel.data.observe(this) {
            if (it.loadingStatus == LoadingStatus.Success) {
                var body = (it as DataResponse.DataSuccess).body
                var booking = (body.body() as BookingModel).result.booking
                viewModel.addNotifi(
                    ItemNotifi(
                        booking.id,
                        "${parts[1].toInt()-1}:${parts[0]}-${parts[2]}/${parts[3].toInt() - 1}/${parts[4]}",
                        "${parts[1]}:${parts[0]}-${parts[2]}/${parts[3].toInt() - 1}/${parts[4]}",
                        (currentTimeMillis / 1000).toInt()
                    )
                )
            }
        }
    }

    var query = ""
    lateinit var dataCache: ArrayList<PackageModel>
    private fun filterList(query: String?) {
        if (query != null) {
            val arr = ArrayList<PackageModel>()
            for (i in arrPackage) {
                if (i.name.contains(query, true)) {
                    arr.add(i)
                }
            }
            dataCache = arr
//            checkSearch = true
            adapterPackage.submitList(dataCache)
        }
    }

    override fun setClick() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
//                query = newText
                adapterPackage.getText(newText)
                filterList(newText)
                return true
            }

        })
        adapterPackage.onclick = { pos ->
            var dialog = DialogCalender(mActivity)
            dialog.init(object : DialogCalender.OnPress {
                override fun ok() {
                    var time = sharedPreferenceUtils.getStringValue(
                        Const.CALANDERDIALOG,
                    )
                    parts = time!!.split("/")

                    mActivity.apply {
                        val alarmManager =
                            mActivity.getSystemService(Context.ALARM_SERVICE) as AlarmManager
                        val alarmIntent = Intent(this, AlarmReceiver::class.java)
                        val calendar = Calendar.getInstance()
                        calendar.apply {
                            set(Calendar.YEAR, parts[4].toInt())
                            set(Calendar.MONTH, parts[3].toInt() - 1)
                            set(Calendar.DAY_OF_MONTH, parts[2].toInt())
                            set(Calendar.HOUR_OF_DAY, parts[1].toInt()-1)
                            set(Calendar.MINUTE, parts[0].toInt())
                            set(Calendar.SECOND, 0)
                        }
                        var triggerTime = calendar.timeInMillis
                        currentTimeMillis = System.currentTimeMillis()
                        val pendingIntent = PendingIntent.getBroadcast(
                            this,
                            (currentTimeMillis / 1000).toInt(), //duy nhat
                            alarmIntent,
                            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
                        )
//                        alarmManager.cancel(pendingIntent)
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
                            Const.token,
                            AddBooking(
                                serviceIds = when (Random.nextInt(0, 4)) {
                                    0 -> {
                                        intArrayOf(32)
                                    }

                                    1 -> {
                                        intArrayOf(33)
                                    }

                                    2 -> {
                                        intArrayOf(34)
                                    }

                                    else -> {
                                        intArrayOf(35)
                                    }
                                },
                                fullName = Const.user.fullName,
                                userId = 0,
                                address = Const.user.address,
                                phoneNumber = Const.user.phoneNumber,
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