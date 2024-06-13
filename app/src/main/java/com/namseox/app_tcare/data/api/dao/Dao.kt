package com.pranksound.fart.airhorn.haircut.data.api.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.pranksound.fart.airhorn.haircut.data.model.ItemModel

@Dao
interface Dao {
    @Query("SELECT * FROM ItemModel")
    fun getAllTheme(): List<ItemModel>

    @Insert
    fun setTheme(sound: ItemModel): Long

    @Delete
    fun deleteTheme(sound: ItemModel): Int

    @Query("SELECT * FROM ItemModel WHERE id = :name")
    fun getItem(name : Int): List<ItemModel>
}