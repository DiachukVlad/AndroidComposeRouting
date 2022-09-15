package com.diachuk.routing.example

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import com.diachuk.routing.*

class UserRoute(private val userName: String) : Route() {
    @Composable
    override fun Content() {
        UserScreen(userName)
    }
}

fun createUserRoute(
    userName: String
) = createRoute("UserRoute_func") {
    UserScreen(userName = userName)
}

@Composable
fun UserScreen(userName: String) {
    Box(modifier = Modifier.fillMaxSize()) {
        Text(text = "UserName = $userName", style = MaterialTheme.typography.h3.copy(color = Color.White))
    }
}

val SendDataScreen by route {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.DarkGray)
    ) {
        RoutingHost(startRoute = SendDataMainScreen)
    }
}

val SendDataMainScreen by route {
    val routing = LocalRouting
    var name by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        TextField(
            value = name,
            onValueChange = { name = it },
            label = { Text(text = "User Name") },
            textStyle = TextStyle(color = Color.White)
        )
        Button(onClick = { routing.navigate(createUserRoute(name)) }) {
            Text(text = "User screen")
        }
        Button(onClick = { routing.navigate(UserRoute(name)) }) {
            Text(text = "User screen class")
        }
    }
}