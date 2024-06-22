package com.namseox.app_tcare.data.api.retrofit.book

import com.namseox.app_tcare.data.model.AddBooking
import com.namseox.app_tcare.data.model.BookingModel
import com.namseox.app_tcare.data.model.RegisterResponse
import com.namseox.app_tcare.data.model.TimeModel
import com.namseox.app_tcare.data.model.UserLoginModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path

interface Booking {
    @POST("/api/bookings")
    suspend fun booking(@Header("Authorization") token: String, @Body user : AddBooking): Response<BookingModel>
    @PATCH("/api/bookings/{id}")
    suspend fun updateBooking(@Path("id") id: Int,
                              @Body data: TimeModel
    ): Response<BookingModel>
}