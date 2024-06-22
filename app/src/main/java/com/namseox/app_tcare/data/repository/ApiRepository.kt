package com.namseox.app_tcare.data.repository

import android.util.Log
import com.namseox.app_tcare.data.api.retrofit.apihelper.ApiHelper
import com.namseox.app_tcare.data.model.AddBooking
import com.namseox.app_tcare.data.model.BookingModel
import com.namseox.app_tcare.data.model.ChangePass
import com.namseox.app_tcare.data.model.RegisterResponse
import com.namseox.app_tcare.data.model.TimeModel
import com.namseox.app_tcare.data.model.UserLoginModel
import com.namseox.app_tcare.data.model.UserLoginModelRe
import retrofit2.Response
import javax.inject.Inject

class ApiRepository @Inject constructor(private val apiHelper: ApiHelper){
    val TAG = "callapi____"

    suspend fun register(user: UserLoginModelRe): Response<RegisterResponse>? {
        return try {
            apiHelper.authApi.register(user)
        } catch (e: Exception) {
            Log.d(TAG, "register:==> "+e)
            null
        }
    }
    suspend fun login(user: UserLoginModel): Response<RegisterResponse>? {
        return try {
            apiHelper.authApi.login(user)
        } catch (e: Exception) {
            Log.d(TAG, "login:==> "+e)
            null
        }
    }

    suspend fun booking(token : String, booking: AddBooking):Response<BookingModel>?{
        return try {
            apiHelper.booking.booking(token,booking)
        }catch (e : Exception){
            Log.d(TAG, "booking:==> "+e)
            null
        }
    }
    suspend fun updateBooking(id : Int, data:TimeModel):Response<BookingModel>?{
        return try {
            apiHelper.booking.updateBooking(id,data)
        }catch (e: Exception){
            Log.d(TAG, "updateBooking:==> "+e)
            null
        }
    }
    suspend fun changePass(token : String, changePass: ChangePass){
         try {
            apiHelper.authApi.changePass(token,changePass)
        }catch (e : Exception){
            Log.d(TAG, "changePass:==> "+e)

        }
    }

//    suspend fun getAllTopic(): List<TopicItemModel>?{
//       return try {
//            apiHelper.topicApi.getAllTopic()
//        }catch (e: Exception){
//            Log.d(TAG, "getalltopic: "+e)
//            null
//        }
//    }
//
//    suspend fun getAllItem(id : String): List<ItemModel>?{
//        return try {
//            apiHelper.subItem.getAllItem(id)
//        }catch (e: Exception){
//            Log.d(TAG, "getallitem: "+e)
//            null
//        }
//    }
}