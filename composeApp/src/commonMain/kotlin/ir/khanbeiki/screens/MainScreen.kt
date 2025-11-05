package ir.khanbeiki.screens

import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.koinScreenModel
import ir.khanbeiki.sqldelight.sample.data.User
import ir.khanbeiki.themes.AppColors
import org.jetbrains.compose.ui.tooling.preview.Preview
import kotlin.random.Random

class MainScreen : Screen {

    @Composable
    override fun Content() {
        val viewModel = koinScreenModel<MainViewModel>()

        MainScreenContent(viewModel)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Preview
@Composable
fun MainScreenContentPreview() {
}

@Composable
fun MainScreenContent(viewModel: MainViewModel) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        containerColor = AppColors.Background,
    ) {
        val getAllUsers by viewModel.getAllUsers.collectAsState()
        var userName by rememberSaveable { mutableStateOf("") }
        var email by rememberSaveable { mutableStateOf("") }
        var isValid by remember { mutableStateOf(false) }

        LaunchedEffect(Unit) {
            viewModel.getAllUsers()
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(
                modifier = Modifier
                    .height(16.dp),
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp)
                    .height(48.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                BasicTextField(
                    value = userName,
                    onValueChange = {
                        userName = it.take(20)
                        isValid = it.length >= 3
                    },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.3f)
                        .height(48.dp)
                        .border(
                            width = 1.dp,
                            color = AppColors.Primary,
                            shape = RoundedCornerShape(12.dp)
                        ).padding(vertical = 8.dp),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    ),
                    decorationBox = { innerTextField ->
                        if (userName.isEmpty()) {
                            Text(
                                modifier = Modifier.wrapContentSize(),
                                text = "Name",
                                color = Color.Gray,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            innerTextField()
                        }
                    }
                )
                Spacer(
                    modifier = Modifier
                        .width(4.dp),
                )
                BasicTextField(
                    value = email,
                    onValueChange = {
                        email = it.take(20)
                    },
                    singleLine = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(0.7f)
                        .height(48.dp)
                        .border(
                            width = 1.dp,
                            color = AppColors.Primary,
                            shape = RoundedCornerShape(12.dp)
                        ).padding(vertical = 8.dp),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Center
                    ),
                    decorationBox = { innerTextField ->
                        if (email.isEmpty()) {
                            Text(
                                modifier = Modifier.wrapContentSize(),
                                text = "Email",
                                color = Color.Gray,
                                fontSize = 14.sp,
                                fontWeight = FontWeight.Normal,
                            )
                        }
                        Box(
                            modifier = Modifier
                                .fillMaxSize(),
                            contentAlignment = Alignment.Center
                        ) {
                            innerTextField()
                        }
                    }
                )
            }

            Button(onClick = {
                viewModel.insertUser(
                    name = userName,
                    email = email,
                )
                viewModel.getAllUsers()
            }, enabled = isValid) {
                Text(text = "Add User")
            }
            ListView(getAllUsers, viewModel)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ListView(list: List<User>?, viewModel: MainViewModel) {
    list?.let { newList ->
        println("printTest 3")
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .wrapContentHeight()
        ) {
            items(
                items = newList,
                key = { user -> user.id }
            ) { user ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(48.dp),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        modifier = Modifier
                            .wrapContentWidth(),
                        text = user.name
                    )
                    Spacer(
                        modifier = Modifier
                            .wrapContentWidth().weight(1f),
                    )
                    Text(
                        modifier = Modifier
                            .wrapContentWidth()
                            .clickable {
                                viewModel.deleteUser(user.id)
                                viewModel.getAllUsers()
                            },
                        text = "Delete",
                        fontStyle = FontStyle.Italic,
                        color = Color.Red
                    )
                }
            }
        }
    }
}