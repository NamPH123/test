package com.pranksound.fart.airhorn.haircut.data.api.retrofit.topic

import com.pranksound.fart.airhorn.haircut.data.model.TopicItemModel
import retrofit2.http.GET

interface TopicApi {
    @GET("/api/pranksound")
    suspend fun getAllTopic(): List<TopicItemModel>
}