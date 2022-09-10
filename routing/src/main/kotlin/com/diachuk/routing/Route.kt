package com.diachuk.routing

import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

abstract class Route(
    val enterTransition: EnterTransition = fadeIn(),
    val exitTransition: ExitTransition = fadeOut(),
) {
    open val name: String? = this::class.simpleName

    @Composable
    abstract fun Content()
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Route) return false

        if (name != other.name) return false

        return true
    }

    override fun hashCode(): Int {
        return name.hashCode()
    }
}


object EmptyRoute : Route() {
    @Composable
    override fun Content() {
        Box(
            Modifier
                .fillMaxSize()
        )
    }
}

