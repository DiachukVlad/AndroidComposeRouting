package com.diachuk.routing.example

import androidx.compose.foundation.layout.RowScope
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import com.diachuk.routing.NavigationBehaviour
import com.diachuk.routing.Route
import com.diachuk.routing.RoutingHost
import com.diachuk.routing.createRoute
import kotlinx.coroutines.flow.MutableStateFlow

val routeFlow = MutableStateFlow(GreenScreen)

val BottomNavigationFlowScreen by createRoute {
    Scaffold(
        bottomBar = {
            BottomFlowBar()
        }
    ) { paddingValues ->
        RoutingHost(
            modifier = Modifier.padding(bottom = paddingValues.calculateBottomPadding()),
            GreenScreen,
            routeFlow,
            NavigationBehaviour.ChangeCurrent
        )
    }
}

@Composable
fun BottomFlowBar() {
    BottomNavigation {
        ExampleBottomNavigationFlowItem(route = GreenScreen, icon = Icons.Default.BrokenImage)
        ExampleBottomNavigationFlowItem(route = RedScreen, icon = Icons.Default.AddAPhoto)
        ExampleBottomNavigationFlowItem(route = MagentaScreen, icon = Icons.Default.Abc)
    }
}

@Composable
fun RowScope.ExampleBottomNavigationFlowItem(route: Route, icon: ImageVector) {
    val currentRoute by routeFlow.collectAsState()

    BottomNavigationItem(selected = currentRoute === route, onClick = {
        routeFlow.tryEmit(route)
    }, icon = {
        Icon(imageVector = icon, contentDescription = "MagentaScreen")
    })
}