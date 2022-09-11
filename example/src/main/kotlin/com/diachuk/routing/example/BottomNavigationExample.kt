package com.diachuk.routing.example

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Abc
import androidx.compose.material.icons.filled.AddAPhoto
import androidx.compose.material.icons.filled.BrokenImage
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import com.diachuk.routing.Route
import com.diachuk.routing.RoutingHost

val BottomNavigationScreen by createRoute {
    val routing = remember { Routing(GreenScreen) }
    Scaffold(
        bottomBar = {
            BottomBar(routing)
        }
    ) { paddingValues ->
        RoutingHost(
            modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding()),
            routing = routing
        )
    }
}

@Composable
fun BottomBar(routing: Routing) {
    BottomNavigation {
        ExampleBottomNavigationItem(routing = routing, route = GreenScreen, icon = Icons.Default.BrokenImage)
        ExampleBottomNavigationItem(routing = routing, route = RedScreen, icon = Icons.Default.AddAPhoto)
        ExampleBottomNavigationItem(routing = routing, route = MagentaScreen, icon = Icons.Default.Abc)
    }
}

@Composable
fun RowScope.ExampleBottomNavigationItem(routing: Routing, route: Route, icon: ImageVector) {
    val currentRoute by routing.currentRoute.collectAsState()

    BottomNavigationItem(selected = currentRoute === route, onClick = {
        routing.changeCurrent(
            route
        )
    }, icon = {
        Icon(imageVector = icon, contentDescription = "MagentaScreen")
    })
}

val GreenScreen by createRoute {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Green)
    )
}

val RedScreen by createRoute {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Red)
    )
}

val MagentaScreen by createRoute {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Magenta)
    )
}