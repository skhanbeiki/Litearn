package ir.khanbeiki

import android.app.Application
import ir.khanbeiki.di.androidModule
import ir.khanbeiki.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppController : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppController)
            modules(appModule, androidModule)
        }
    }
}