package com.diachuk.routing

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.with
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import kotlinx.coroutines.flow.Flow


private val LocalRoutingComposition = compositionLocalOf { Routing(EmptyRoute) }
val LocalRouting @Composable get() = LocalRoutingComposition.current

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun RoutingHost(
    modifier: Modifier = Modifier,
    routing: Routing = remember { Routing(EmptyRoute) }
) {
    CompositionLocalProvider(LocalRoutingComposition provides routing) {
        BackHandler(LocalRouting.backStack.size > 0) {
            routing.navigateBack()
        }
        val targetState = routing.currentRoute.collectAsState()

        AnimatedContent(
            targetState = targetState.value,
            modifier = modifier,
            transitionSpec = {
                (routing.forceEnterTransition ?: targetState.value.enterTransition) with
                        (routing.forceExitTransition ?: initialState.exitTransition)
            }

        ) { route ->
            route.Content()
        }
    }
}

@Composable
fun RoutingHost(
    modifier: Modifier = Modifier,
    startRoute: Route
) {
    RoutingHost(
        modifier,
        routing = remember {
            Routing(startRoute)
        }
    )
}

@Composable
fun RoutingHost(
    modifier: Modifier = Modifier,
    start: Route = EmptyRoute,
    flowRoute: Flow<Route>,
    behaviour: NavigationBehaviour
) {
    RoutingHost(
        modifier,
        routing = remember {
            Routing(start, flowRoute, behaviour)
        }
    )
}
