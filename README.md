[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.vldi01/android-compose-routing/badge.svg)](https://search.maven.org/artifact/io.github.vldi01/android-compose-routing/1.1.2/jar)
# Routing for Android Compose
Routing feature for [Compose Android](https://developer.android.com/jetpack/compose/documentation)

## Advantages
- Small
- Easy to use
- Animations
- Kotlin Flow support

## Disadvantages
- You have to take care of deep link by yourself

## Quick start
### 1. Add dependency
Add Maven Central to your repositories
```gradle
repositories {
    mavenCentral()
    ...
}
```

Add the dependency
```gradle
dependencies {
    implementation("io.github.vldi01:android-compose-routing:1.1.2")
    ...
}
```
### 2. Create your first route
```kotlin
val MainScreen by createRoute {
    Box(Modifier.fillMaxSize())
}
```
### 3. Define RoutingHost
For example in your onCreate method
```kotlin
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
        RoutingHost(startRoute = MainScreen)
    }
}
```
### 4. Navigating
To change the screen simply run the ```routing.navigate``` method.

You can easily get the ```Routing``` object using ```LocalRouting``` value. It is Composable Local Composition. 
So it is available in any composable function inside the ```RoutingHost```

To learn more about local compositing check the [official docs](https://developer.android.com/jetpack/compose/compositionlocal).

```kotlin
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
    }
}
```

#### You can navigate with animation.
To do that just set up the ```forceExitTransition``` and ```forceEnterTransition``` in ```routing.navigate``` method like in the example above.
It will trigger animation once. 
If you want to setup default enter and exit animations for a screen just do it in ```createRoute```

**By default every screen has fadeIn fadeOut animations.** See [Compose Animations](https://developer.android.com/jetpack/compose/animation#enter-exit-transition) for more transitions.

```kotlin
val MainScreen by createRoute(
    enterTransition = fadeIn(),
    exitTransition = fadeOut()
) {
    Box(Modifier.fillMaxSize())
}
```

# Advanced navigation

## RoutingHost
A composable function. It holds ```Routing``` and shows the screen that you want with animation that you want.

## Routing 
A main object. It holds current route, back stack and provides methods for navigation.

### Methods
- ```navigate``` It changes the current screen and add the previous one to the back stack.
- ```changeCurrent``` It changes current screen without adding the previous one to the back stack.
- ```navigateWithoutRepeating``` It changes the current screen and add the previous one to the back stack. But it also doesn't allow repeating screens in the back stack. So before changing the screen it check whether your current screen appears in the back stack. If so it removes it and put the current screen on top of back stack. 
- ```navigateBack``` It shows the highest screen in the back stack and removes curernt one from the composition.

### Variables
- ```backStack``` It is a mutable array and you can modify it as you want.
- ```currentRoute``` It is a StateFlow that holds current route.

## Route
Just a view holder. But it also holds the animations.

## EmptyRoute
Singletone object. It is just a screen with a ```Box(Modifier.fillMaxSize())```

## Kotlin Flow Support
First of all Routing has a ```currentRoute``` value which is StateFlow. So you can observe it or use as a state inside your composable funtion.

Also, you can make routing object observe your Flow of routes. You have to provide the start route, your Flow object and the behavior. 

There are three different behaiours.
1. Navigate. It calls ```routing.navigate()``` every time route is changed.
2. ChangeCurrent. It calls ```routing.changeCurrent()``` every time route is changed.
3. NavigateWithoutRepeating. It calls ```routing.navigateWithoutRepeating()``` every time route is changed.
For more info see [Routing Methods](#routinghost)

## Nested routing host
It is allowed to use ```RoutingHost``` inside another ```RoutingHost```. 
```LocalRouting``` returns the local routing. because it is Locally scoped. 
For the example check the [NestedRoutingExample](https://github.com/vldi01/AndroidComposeRouting/blob/master/example/src/main/kotlin/com/diachuk/routing/example/NestedRoutingExample.kt)


## Passing the data
If you want to pass the data to your screen you have to create you own class that extends ```Route```. You can pass the data inside the constructor and use it in content method.
```kotlin
class UserRoute(private val userName: String) : Route() {
    @Composable
    override fun Content() {
        Box(modifier = Modifier.fillMaxSize()) {
            Text(text = "UserName = $userName", style = MaterialTheme.typography.h3.copy(color = Color.White))
        }
    }
}
```


