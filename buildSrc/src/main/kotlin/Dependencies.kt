
object Versions {
    const val kotlinVersion = "1.7.10"

    const val kotlinCoroutines = "1.6.4"
    const val kotlinxDateTime = "0.4.0"
    const val kotlinSerialization = "1.3.3"

    const val compose = "1.2.1"
    const val composeCompiler = "1.3.0"
    const val navCompose = "2.5.1"
    const val accompanist = "0.25.1"

    const val junit = "4.13"

    const val androidGradle = "7.2.2"

}

object AndroidSdk {
    const val min = 21
    const val compile = 33
    const val target = compile
}

object Kotlin {
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kotlinCoroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.kotlinCoroutines}"
}

object Compose {
    const val compiler = "androidx.compose.compiler:compiler:${Versions.composeCompiler}"
    const val ui = "androidx.compose.ui:ui:${Versions.compose}"
    const val runtime = "androidx.compose.runtime:runtime:${Versions.compose}"
    const val uiGraphics = "androidx.compose.ui:ui-graphics:${Versions.compose}"
    const val uiTooling = "androidx.compose.ui:ui-tooling:${Versions.compose}"
    const val foundationLayout = "androidx.compose.foundation:foundation-layout:${Versions.compose}"
    const val material = "androidx.compose.material:material:${Versions.compose}"
    const val materialIconsCore = "androidx.compose.material:material-icons-core:${Versions.compose}"
    const val materialIconsExtended = "androidx.compose.material:material-icons-extended:${Versions.compose}"
    const val navigation = "androidx.navigation:navigation-compose:${Versions.navCompose}"
}


