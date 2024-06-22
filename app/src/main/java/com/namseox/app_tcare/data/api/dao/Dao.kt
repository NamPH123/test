package com.namseox.app_tcare.data.api.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.namseox.app_tcare.data.model.ItemNotifi

@Dao
interface Dao {
    @Query("SELECT * FROM ItemNotifi")
    fun getAllTheme(): List<ItemNotifi>

    @Insert
    fun setTheme(sound: ItemNotifi): Long

    @Delete
    fun deleteTheme(sound: ItemNotifi): Int

    @Query("UPDATE ItemNotifi SET time = :newAttributeA WHERE id = :itemId")
     fun updateItem(itemId: Int, newAttributeA: String)
}