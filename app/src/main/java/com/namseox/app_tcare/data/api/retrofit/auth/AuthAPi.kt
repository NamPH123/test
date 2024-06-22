package com.namseox.app_tcare.data.api.retrofit.auth

import com.namseox.app_tcare.data.model.AddBooking
import com.namseox.app_tcare.data.model.BookingModel
import com.namseox.app_tcare.data.model.ChangePass
import com.namseox.app_tcare.data.model.RegisterResponse
import com.namseox.app_tcare.data.model.UserLoginModel
import com.namseox.app_tcare.data.model.UserLoginModelRe
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

interface AuthAPi {
    @POST("/api/auth/login")
    suspend fun login(@Body user : UserLoginModel): Response<RegisterResponse>

    @POST("/api/auth/customer-register")
    suspend fun register(@Body user : UserLoginModelRe): Response<RegisterResponse>

    @POST("/api/users/change-password")
    suspend fun changePass(@Header("Authorization") token: String, @Body user : ChangePass)
}