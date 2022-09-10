package com.diachuk.routing

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class Routing(start: Route) {
    private val _currentRoute = MutableStateFlow(start)
    val currentRoute = _currentRoute.asStateFlow()

    val backStack = ArrayList<Route>()
    var forceExitTransition: ExitTransition? = null
    var forceEnterTransition: EnterTransition? = null
    val prevRoute get() = backStack.lastOrNull()

    constructor(start: Route, flowRoute: Flow<Route>, behaviour: NavigationBehaviour) : this(start) {
        CoroutineScope(Dispatchers.Default).launch {
            flowRoute.collect { screen ->
                when (behaviour) {
                    NavigationBehaviour.Navigate -> navigate(screen)
                    NavigationBehaviour.ChangeCurrent -> changeCurrent(screen)
                    NavigationBehaviour.NavigateWithoutRepeating -> navigateWithoutRepeating(screen)
                }
            }
        }
    }

    fun changeCurrent(
        nextRoute: Route,
        forceExitTransition: ExitTransition? = null,
        forceEnterTransition: EnterTransition? = null
    ) {
        this.forceEnterTransition = forceEnterTransition
        this.forceExitTransition = forceExitTransition

        _currentRoute.value = nextRoute
    }

    fun navigate(
        nextRoute: Route,
        forceExitTransition: ExitTransition? = null,
        forceEnterTransition: EnterTransition? = null
    ) {
        backStack.add(_currentRoute.value)
        changeCurrent(nextRoute, forceExitTransition, forceEnterTransition)
    }

    fun navigateWithoutRepeating(
        nextRoute: Route,
        forceExitTransition: ExitTransition? = null,
        forceEnterTransition: EnterTransition? = null
    ) {
        backStack.removeAll { it == nextRoute }
        backStack.add(_currentRoute.value)
        changeCurrent(nextRoute, forceExitTransition, forceEnterTransition)
    }

    fun navigateBack(
        forceExitTransition: ExitTransition? = null,
        forceEnterTransition: EnterTransition? = null
    ) {
        prevRoute?.let {
            changeCurrent(it, forceExitTransition, forceEnterTransition)
            backStack.removeLast()
        }
    }
}

enum class NavigationBehaviour {
    Navigate, ChangeCurrent, NavigateWithoutRepeating
}