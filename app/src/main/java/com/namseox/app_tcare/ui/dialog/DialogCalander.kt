package com.namseox.app_tcare.ui.dialog

import android.app.Activity
import com.namseox.app_tcare.R
import com.namseox.app_tcare.databinding.DialogCalanderBinding
import com.namseox.app_tcare.utils.Const
import com.namseox.app_tcare.utils.SharedPreferenceUtils
import com.namseox.app_tcare.utils.checkDay
import com.namseox.app_tcare.view.base.AbsDialog
import java.util.Calendar

class DialogCalender(context: Activity) :
AbsDialog<DialogCalanderBinding>(context, false) {

    var sharedPreferenceUtils: SharedPreferenceUtils = SharedPreferenceUtils.getInstance(context)
    private lateinit var onPress: OnPress
    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var minute = 0

    interface OnPress {
        fun ok()
        fun cancel()

    }

    fun init(onPress: OnPress?) {
        this.onPress = onPress!!
    }

    override fun getContentView(): Int = R.layout.dialog_calander

    override fun initView() {
        val parts = getCurrentDateTimeParts()
        minute = parts[0].toInt()
        hour = parts[1].toInt()
        day = parts[2].toInt()
        month = parts[3].toInt()
        year = parts[4].toInt()
        binding.dp.init(year, month - 1, day, null)
        binding.npHours.minValue = 0
        binding.npHours.maxValue = 23
        binding.npMinute.minValue = 0
        binding.npMinute.maxValue = 59

        binding.npMinute.setValue(minute)
        binding.npHours.setValue(hour)
        month = Calendar.getInstance().get(Calendar.MONTH) + 1
        year = Calendar.getInstance().get(Calendar.YEAR)
    }


    fun save() {
        year = binding.dp.year
        month = binding.dp.month + 1
        day = binding.dp.dayOfMonth
        hour = binding.npHours.value
        minute = binding.npMinute.value
        sharedPreferenceUtils.putStringValue(
            Const.CALANDERDIALOG,
            "$minute/$hour/$day/$month/$year"
        )
        dismiss()
    }

    override fun bindView() {
        binding.btnOk.setOnClickListener {
            day = Calendar.getInstance().get(Calendar.DAY_OF_MONTH) - 1
            hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY)
            minute = Calendar.getInstance().get(Calendar.MINUTE)
            if (checkDay(
                    context,
                    binding.dp.year,
                    binding.dp.month,
                    binding.dp.dayOfMonth,
                    binding.npHours.value,
                    binding.npMinute.value
                )
            ) {
                save()
                var dialog = DialogText(context)
                dialog.init(object  : DialogText.OnPresss{
                    override fun ok() {
                        onPress.ok()
                        this@DialogCalender.dismiss()
                    }

                    override fun cancel() {

                    }

                })

                dialog.show()

            }


        }
        binding.btnCancal.setOnClickListener {
            onPress.cancel()
            dismiss()
        }
    }
    fun getCurrentDateTimeParts(): List<Int> {
        val calendar = Calendar.getInstance()
        val minute = calendar.get(Calendar.MINUTE)
        val hour = calendar.get(Calendar.HOUR_OF_DAY) // Sử dụng HOUR_OF_DAY để lấy giờ theo định dạng 24 giờ
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val month = calendar.get(Calendar.MONTH) + 1 // Tháng bắt đầu từ 0 nên cộng thêm 1 để có tháng thực tế
        val year = calendar.get(Calendar.YEAR)

        return listOf(minute, hour, day, month, year)
    }
}