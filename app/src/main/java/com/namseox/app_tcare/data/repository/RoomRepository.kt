package com.namseox.app_tcare.data.repository

import android.content.Context
import android.util.Log
import com.namseox.app_tcare.data.api.base.BaseRoomDBHelper
import com.namseox.app_tcare.data.model.ItemNotifi

class RoomRepository(context: Context) : BaseRoomDBHelper(context) {

    suspend fun getAllItemInfo(): List<ItemNotifi> {
        return try {
            db.dbDao().getAllTheme()
        } catch (e: Exception) {
            Log.d("TAG", "exception_of_app getAllCallPhone from roomDB: ${e} ")
            arrayListOf<ItemNotifi>()
        }
    }

   suspend fun setItemInfo(callPhoneModel: ItemNotifi): Long {
        return try {
            db.dbDao().setTheme(callPhoneModel)
        } catch (e: Exception) {
            Log.d("TAG", "exception_of_app setCallPhone from roomDB: ${e} ")
            -1
        }
    }

    suspend fun deleteItemInfo(voiceModel: ItemNotifi): Int {
        return try {
            db.dbDao().deleteTheme(voiceModel)
        } catch (e: Exception) {
            Log.d("TAG", "exception_of_app setVoice from roomDB: ${e} ")
            -1
        }
    }
    suspend fun updateItemInfo(id : Int, chuoi : String) {
         try {
            db.dbDao().updateItem(id, chuoi)
        } catch (e: Exception) {
            Log.d("TAG", "exception_of_app setVoice from roomDB: ${e} ")

        }
    }
}