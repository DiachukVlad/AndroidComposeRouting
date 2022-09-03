plugins {
    id("com.android.library")
    id("maven-publish")
    kotlin("android")
}

group = "com.diachuk.routing"
version = "1.0"

android {
    namespace = "com.diachuk.routing"

    compileSdk = AndroidSdk.compile
    sourceSets["main"].manifest.srcFile("src/main/AndroidManifest.xml")

    defaultConfig {
        minSdk = AndroidSdk.min
        targetSdk = AndroidSdk.target
        aarMetadata {
            minCompileSdk = 21
        }
    }

    lint {
        abortOnError = false
    }

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Versions.composeCompiler
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
}

dependencies {
    with(Compose) {
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