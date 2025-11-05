package ir.khanbeiki.di

import ir.khanbeiki.db.MainRepository
import ir.khanbeiki.db.MainRepositoryImpl
import ir.khanbeiki.db.createDatabase
import ir.khanbeiki.screens.MainViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val appModule: Module = module {
    single { createDatabase(get()) }

    single<MainRepository> { MainRepositoryImpl(get()) }
    factory { MainViewModel(get()) }
}