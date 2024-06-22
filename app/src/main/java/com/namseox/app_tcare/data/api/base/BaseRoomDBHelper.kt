package com.namseox.app_tcare.data.api.base

import android.content.Context
import androidx.room.Room
import com.namseox.app_tcare.utils.SingletonHolder
import com.namseox.app_tcare.data.api.repository.AppDB

open class BaseRoomDBHelper(context: Context) {
    val db = Room.databaseBuilder(context, AppDB::class.java,"Tcare").build()
    companion object : SingletonHolder<BaseRoomDBHelper, Context>(::BaseRoomDBHelper)
}