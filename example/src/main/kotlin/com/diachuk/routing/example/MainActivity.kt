package com.diachuk.routing.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideIn
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.IntOffset
import com.diachuk.routing.LocalRouting
import com.diachuk.routing.RoutingHost
import com.diachuk.routing.createRoute
import com.diachuk.routing.example.ui.theme.NestedRoutingScreen
import com.diachuk.routing.example.ui.theme.RoutingTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            RoutingTheme {
                RoutingHost(startRoute = MainScreen)
            }
        }
    }
}

val MainScreen by createRoute {
    Column(Modifier.fillMaxSize()) {
        val routing = LocalRouting

        Button(onClick = { routing.navigate(BlueScreen) }) {
            Text(text = "Blue screen")
        }

        Button(onClick = {
            routing.navigate(
                BlueScreen,
                forceExitTransition = fadeOut(),
                forceEnterTransition = slideIn(spring(Spring.DampingRatioHighBouncy, Spring.StiffnessLow)) {
                    IntOffset(it.width, 0)
                }
            )
        }) {
            Text(text = "Blue screen with custom animation")
        }

        Button(onClick = { routing.navigate(NestedRoutingScreen) }) {
            Text(text = "Nested Routing")
        }

        Button(onClick = { routing.navigate(BottomNavigationScreen) }) {
            Text(text = "Bottom Navigation")
        }
        Button(onClick = { routing.navigate(SendDataScreen) }) {
            Text(text = "Send data")
        }
        Button(onClick = { routing.navigate(BottomNavigationFlowScreen) }) {
            Text(text = "Bottom Navigation with Flow")
        }
    }
}

val BlueScreen by createRoute {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Blue)
    )
}