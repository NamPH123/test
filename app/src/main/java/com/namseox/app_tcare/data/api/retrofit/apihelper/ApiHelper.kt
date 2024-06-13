package com.pranksound.fart.airhorn.haircut.data.api.retrofit.apihelper

import android.content.Context
import com.google.gson.GsonBuilder
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.pranksound.fart.airhorn.haircut.data.api.base.BaseRetrofitHelper
import com.pranksound.fart.airhorn.haircut.data.api.retrofit.subitem.SubItemApi
import com.pranksound.fart.airhorn.haircut.data.api.retrofit.topic.TopicApi
import com.pranksound.fart.airhorn.haircut.utils.SingletonHolder
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

class ApiHelper(context: Context) : BaseRetrofitHelper() {
    private val BASE_URL = "http://lvtglobal.site/"
    var topicApi: TopicApi
    var subItem: SubItemApi

    init {
        GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder().baseUrl(BASE_URL)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory()).client(okHttpClient!!).build()
        topicApi = retrofit.create(TopicApi::class.java)
        subItem = retrofit.create(SubItemApi::class.java)
    }

    companion object : SingletonHolder<ApiHelper, Context>(::ApiHelper)
}