package com.svoboda.database.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.svoboda.database.domain.model.FavoriteProduct

@Database(
    entities = [FavoriteProduct::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun productDao(): FavoriteProductDao
}
