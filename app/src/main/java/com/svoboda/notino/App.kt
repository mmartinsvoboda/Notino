package com.svoboda.notino

import android.app.Application
import com.svoboda.database.databaseModule
import com.svoboda.network.koin.networkModule
import com.svoboda.products.productsModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

/**
 * This list should contain all the koin modules that are used by application!
 */
internal val APP_MODULES = databaseModule + networkModule + productsModule

/**
 * The Application class of the app module.
 */
class App : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@App)
            modules(APP_MODULES)
        }
    }
}
