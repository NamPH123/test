package com.namseox.app_tcare.data.api.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.namseox.app_tcare.data.api.dao.Dao
import com.namseox.app_tcare.data.model.ItemNotifi
import javax.inject.Singleton

@Singleton
@Database(entities = [ItemNotifi::class], version = 1, exportSchema = false)
abstract class AppDB: RoomDatabase() {
    abstract fun dbDao(): Dao
}