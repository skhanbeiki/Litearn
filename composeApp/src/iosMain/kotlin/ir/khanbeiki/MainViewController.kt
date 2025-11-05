package ir.khanbeiki

import androidx.compose.ui.window.ComposeUIViewController
import ir.khanbeiki.di.appModule
import ir.khanbeiki.di.iosModule
import org.koin.core.context.startKoin
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController {
    startKoin {
        modules(appModule, iosModule)
    }
    return ComposeUIViewController { App() }
}