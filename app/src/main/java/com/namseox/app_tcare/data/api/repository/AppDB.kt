package com.pranksound.fart.airhorn.haircut.data.api.repository

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pranksound.fart.airhorn.haircut.data.api.dao.Dao
import com.pranksound.fart.airhorn.haircut.data.model.ItemModel
import javax.inject.Singleton

@Singleton
@Database(entities = [ItemModel::class], version = 1, exportSchema = false)
abstract class AppDB: RoomDatabase() {
    abstract fun dbDao(): Dao
}