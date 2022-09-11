package com.diachuk.routing

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import kotlin.reflect.KProperty

interface CreateRouteDelegate {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): Route
}

private class CreateRouteDelegateImpl(
    val enterTransition: EnterTransition = fadeIn(),
    val exitTransition: ExitTransition = fadeOut(),
    val content: @Composable () -> Unit
) : CreateRouteDelegate {
    private object UNINITIALIZED

    @Volatile
    private var _value: Any? = UNINITIALIZED

    override operator fun getValue(thisRef: Any?, property: KProperty<*>): Route {
        val _v1 = _value
        if (_v1 !== UNINITIALIZED) {
            return _v1 as Route
        }

        return synchronized(this) {
            val _v2 = _value
            if (_v2 !== UNINITIALIZED) {
                (_v2 as Route)
            } else {
                _value = object : Route(enterTransition, exitTransition) {
                    override val name = property.name

                    @Composable
                    override fun Content() {
                        content()
                    }
                }
                _value as Route
            }
        }
    }
}

fun createRoute(
    enterTransition: EnterTransition = fadeIn(),
    exitTransition: ExitTransition = fadeOut(),
    content: @Composable () -> Unit
): CreateRouteDelegate = CreateRouteDelegateImpl(enterTransition, exitTransition, content)