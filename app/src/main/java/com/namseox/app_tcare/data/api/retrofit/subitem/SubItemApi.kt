package com.pranksound.fart.airhorn.haircut.data.api.retrofit.subitem

import com.pranksound.fart.airhorn.haircut.data.model.ItemModel
import retrofit2.http.GET
import retrofit2.http.Path

interface SubItemApi {
    @GET("/api/pranksound/{id}")
    suspend fun getAllItem(@Path("id") i: String):  List<ItemModel>
}