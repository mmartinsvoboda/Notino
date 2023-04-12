package com.svoboda.database

import androidx.room.Room
import com.svoboda.database.data.AppDatabase
import com.svoboda.database.data.FavoriteProductRepositoryImpl
import com.svoboda.database.domain.repository.FavoriteProductRepository
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val databaseModule = module {
    single {
        Room.databaseBuilder(
            androidContext(),
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    single { get<AppDatabase>().productDao() }
}

