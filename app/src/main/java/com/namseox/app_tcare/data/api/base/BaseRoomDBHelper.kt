package com.pranksound.fart.airhorn.haircut.data.api.base

import android.content.Context
import androidx.room.Room
import com.pranksound.fart.airhorn.haircut.data.api.repository.AppDB
import com.pranksound.fart.airhorn.haircut.utils.SingletonHolder

open class BaseRoomDBHelper(context: Context) {
    val db = Room.databaseBuilder(context, AppDB::class.java,"BatteryCharging").build()
    companion object : SingletonHolder<BaseRoomDBHelper, Context>(::BaseRoomDBHelper)
}