package com.namseox.app_tcare.ui.infomanager

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.activity.viewModels
import androidx.core.app.AlarmManagerCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.namseox.app_tcare.R
import com.namseox.app_tcare.data.model.ItemNotifi
import com.namseox.app_tcare.data.model.TimeModel
import com.namseox.app_tcare.databinding.ActivityInforManagerBinding
import com.namseox.app_tcare.service.AlarmReceiver
import com.namseox.app_tcare.ui.dialog.DialogCalender
import com.namseox.app_tcare.ui.home.MainViewModel
import com.namseox.app_tcare.utils.Const
import com.namseox.app_tcare.utils.SharedPreferenceUtils
import com.namseox.app_tcare.view.base.AbsBaseActivity
import dagger.hilt.android.AndroidEntryPoint
import java.util.Calendar
import javax.inject.Inject

@AndroidEntryPoint
class InfoManagerActivity : AbsBaseActivity<ActivityInforManagerBinding>(false) {
    @Inject
    lateinit var sharedPreferenceUtils: SharedPreferenceUtils
    val viewModel: MainViewModel by viewModels()

    lateinit var adapter: InfoManagerAdapter
    var arr = arrayListOf<ItemNotifi>()
    override fun getFragmentID(): Int = 0

    lateinit var parts: List<String>
    override fun getLayoutId(): Int = R.layout.activity_infor_manager
    var currentTimeMillis = 0L
    override fun init() {
        viewModel.getAllItem()
        adapter = InfoManagerAdapter()
        binding.rcv.adapter = adapter
        binding.rcv.layoutManager = GridLayoutManager(this, 1, RecyclerView.VERTICAL, false)
        viewModel.dataInfo.observe(this) {
            arr.clear()
            arr = it as ArrayList<ItemNotifi>
            adapter.submitList(arr)
        }
        adapter.onclick = { pos ->
            var dialog = DialogCalender(this)
            dialog.init(object : DialogCalender.OnPress {
                override fun ok() {
                    var time = sharedPreferenceUtils.getStringValue(
                        Const.CALANDERDIALOG,
                    )
                    parts = time!!.split("/")
                    val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
                    val alarmIntent = Intent(this@InfoManagerActivity, AlarmReceiver::class.java)
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
                    currentTimeMillis = System.currentTimeMillis()
                    val pendingIntent = PendingIntent.getBroadcast(
                        this@InfoManagerActivity,
                        arr[pos].idPost, //duy nhat
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
                    viewModel.updateItem(
                        arr[pos].id,
                        "${parts[1].toInt() - 1}:${parts[0]}-${parts[2]}/${parts[3]}/${parts[4]}"
                    )
                    viewModel.updateBooking(
                        arr[pos].id,
                        TimeModel(
                            "${parts[1].toInt() - 1}:${parts[0]}",
                            "${parts[2]}-${parts[3]}-${parts[4]}",
                            0
                        )
                    )
                }

                override fun cancel() {

                }

            })
        }
    }
}