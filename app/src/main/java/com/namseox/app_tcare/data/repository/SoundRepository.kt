package com.pranksound.fart.airhorn.haircut.data.repository

import android.content.Context
import android.util.Log
import com.pranksound.fart.airhorn.haircut.data.api.base.BaseRoomDBHelper
import com.pranksound.fart.airhorn.haircut.data.model.ItemModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.lang.Exception

class SoundRepository(context: Context): BaseRoomDBHelper(context) {
    fun getAllSound(): List<ItemModel>{
        return try {
            db.dbDao().getAllTheme()
        } catch (e: Exception) {
            Log.d("TAG", "exception_of_app getAllCallPhone from roomDB: ${e} ")
            arrayListOf<ItemModel>()
        }
    }
    fun setSound(theme : ItemModel):Long{
        return try {
            db.dbDao().setTheme(theme)
        } catch (e: Exception) {
            Log.d("TAG", "exception_of_app setCallPhone from roomDB: ${e} ")
            -1
        }
    }

    fun delete(theme : ItemModel):Int{
        return try {
            db.dbDao().deleteTheme(theme)
        } catch (e: Exception) {
            Log.d("TAG", "exception_of_app setCallPhone from roomDB: ${e} ")
            -1
        }
    }
    suspend fun getIemByid(id : Int):List<ItemModel>{
        return try {
            withContext(Dispatchers.IO){
                db.dbDao().getItem(id)
            }
        } catch (e: Exception) {
            Log.d("TAG", "exception_of_app setCallPhone from roomDB: ${e} ")
            arrayListOf()
        }
    }

}