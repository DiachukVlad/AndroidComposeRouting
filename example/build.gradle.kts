plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    compileSdk = AndroidSdk.compile
    defaultConfig {
        applicationId = "com.diachuk.rooting.example"
        minSdk = AndroidSdk.min
        targetSdk = AndroidSdk.target

        versionCode = 1
        versionName = "1.0"
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
    namespace = "com.diachuk.rooting.example"
}


kotlin {
    sourceSets.all {
        languageSettings {
            optIn("androidx.compose.material.ExperimentalMaterialApi")
            optIn("kotlin.RequiresOptIn")
        }
    }
}

dependencies {
    implementation("androidx.activity:activity-compose:1.5.1")
    implementation("com.google.android.material:material:1.6.1")

//    implementation(project(":routing"))
    implementation("io.github.vldi01:android-compose-routing:1.1.2")

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
}