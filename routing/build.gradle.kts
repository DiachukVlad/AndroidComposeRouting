plugins {
    id("com.android.library")
    kotlin("android")
}

group = "com.diachuk.routing"
version = "1.0"

android {
    compileSdk = AndroidSdk.compile
    sourceSets["main"].manifest.srcFile("src/main/AndroidManifest.xml")
    defaultConfig {
        minSdk = AndroidSdk.min
        targetSdk = AndroidSdk.target
    }
    lint {
        abortOnError = false
    }
}

dependencies {
    implementation("androidx.activity:activity-compose:1.5.1")
    implementation("com.google.android.material:material:1.6.1")

    with (Compose) {
        implementation(compiler)
        implementation(ui)
        implementation(uiGraphics)
        implementation(uiTooling)
        implementation(foundationLayout)
        implementation(material)
        implementation(materialIconsCore)
        implementation(materialIconsExtended)
        implementation(navigation)
    }

    with(Kotlin) {
        implementation(coroutinesCore)
        implementation(coroutinesAndroid)
    }
}

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "16"
}