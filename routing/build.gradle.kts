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

publishing {
    publications {
        create<MavenPublication>("gpr") {
            run {
                groupId = "com.diachuk.routing"
                artifactId = "android-compose-routing"
                version = "1.0"
                artifact("$buildDir/outputs/aar/routing-release.aar")
            }
        }
    }
    repositories {
        maven {
            name = "GitHubPackages"

            url = uri("https://maven.pkg.github.com/DiachukVlad/AndroidComposeRouting") // Github Package
            credentials {
                //Fetch these details from the properties file or from Environment variables
                username = project.properties.get("gpr.usr") as String? ?: System.getenv("GPR_USER")
                password = project.properties.get("gpr.key") as String? ?: System.getenv("GPR_API_KEY")
            }
        }
    }
}