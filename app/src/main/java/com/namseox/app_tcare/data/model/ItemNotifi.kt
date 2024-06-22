package com.namseox.app_tcare.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
class ItemNotifi (
    @PrimaryKey
    var id : Int,
    var time : String,  //thoi gian bao thuc
    var timeHen: String,
    var idPost : Int   //loại bỏ báo thức đã cài
    )