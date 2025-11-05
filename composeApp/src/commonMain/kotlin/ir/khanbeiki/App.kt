package ir.khanbeiki

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import ir.khanbeiki.screens.MainScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

@Composable
@Preview
fun App() {
    MaterialTheme {
        Scaffold(
            modifier = Modifier
                .fillMaxSize()
        ) { innerPadding ->
            Navigator(MainScreen()) { navigator ->
                SlideTransition(navigator)
            }
        }
    }
}