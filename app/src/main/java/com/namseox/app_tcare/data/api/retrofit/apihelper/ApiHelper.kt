package com.namseox.app_tcare.data.api.retrofit.apihelper

import android.content.Context
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.namseox.app_tcare.data.api.base.BaseRetrofitHelper
import com.namseox.app_tcare.data.api.retrofit.auth.AuthAPi
import com.namseox.app_tcare.data.api.retrofit.book.Booking
import com.namseox.app_tcare.utils.SingletonHolder
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiHelper(context: Context) : BaseRetrofitHelper() {
//    private val BASE_URL = "http://10.0.2.2:3000/"
private val BASE_URL = "https://7be8-58-186-68-61.ngrok-free.app"
    var authApi: AuthAPi
var booking: Booking

    init {
        GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory()).client(okHttpClient!!).build()
        authApi = retrofit.create(AuthAPi::class.java)
        booking = retrofit.create(Booking::class.java)
    }

    companion object : SingletonHolder<ApiHelper, Context>(::ApiHelper)
}