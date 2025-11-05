package ir.khanbeiki

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.systemBars
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import ir.khanbeiki.themes.AppColors
import ir.khanbeiki.utils.provideAppContext

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)
        provideAppContext(applicationContext)

        setContent {
            Content()
        }
    }
}

@Preview
@Composable
fun AppAndroidPreview() {
    Content()
}

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Content() {
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(WindowInsets.systemBars.asPaddingValues().calculateTopPadding())
                    .background(AppColors.Primary)
            )
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1f)
            ) {
                App()
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(
                        WindowInsets.systemBars.asPaddingValues().calculateBottomPadding()
                    )
            )
        }
    }
}

