package ir.khanbeiki.di
import ir.khanbeiki.db.DatabaseFactory
import org.koin.dsl.module

val iosModule = module {
    single { DatabaseFactory() }
}