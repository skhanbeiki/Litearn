package ir.khanbeiki.di

import ir.khanbeiki.db.DatabaseFactory
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val androidModule = module {
    single { DatabaseFactory(androidContext()) }
}