package com.namseox.app_tcare.data.model

data class BookingModel(
    val code: Int,
    val message: String,
    val result: Result,
    val statusCode: Int,
    val timestamp: String
)
data class Booking(
    val bookingDate: String,
    val branchId: Int,
    val createdAt: String,
    val id: Int,
    val merchantId: Int,
    val services: List<Any>,
    val startTime: String,
    val status: Int,
    val updatedAt: String,
    val userId: Int
)
data class Result(
    val booking: Booking
)

data class AddBooking(
    var serviceIds: IntArray = intArrayOf(34,33),
    var fullName: String?,
    var userId : Int?,
    var address : String?,
    var branchId : Int = 20,
    var phoneNumber : String?,
    var startTime: String?,
    var bookingDate: String?
)

data class TimeModel(
    var startTime : String?,
    var bookingDate : String?,
    var status : Int ?
)