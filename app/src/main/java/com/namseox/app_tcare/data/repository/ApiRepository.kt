package com.pranksound.fart.airhorn.haircut.data.repository

import android.content.Context
import android.util.Log
import com.pranksound.fart.airhorn.haircut.data.api.retrofit.apihelper.ApiHelper
import com.pranksound.fart.airhorn.haircut.data.model.ItemModel
import com.pranksound.fart.airhorn.haircut.data.model.TopicItemModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiHelper: ApiHelper){
    val TAG = "callapi"
    suspend fun getAllTopic(): List<TopicItemModel>?{
       return try {
            apiHelper.topicApi.getAllTopic()
        }catch (e: Exception){
            Log.d(TAG, "getalltopic: "+e)
            null
        }
    }

    suspend fun getAllItem(id : String): List<ItemModel>?{
        return try {
            apiHelper.subItem.getAllItem(id)
        }catch (e: Exception){
            Log.d(TAG, "getallitem: "+e)
            null
        }
    }
}