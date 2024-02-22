plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    id("app.cash.sqldelight") version "2.0.1"
    kotlin("plugin.serialization") version "1.9.21"
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            implementation("io.insert-koin:koin-core:3.5.3")
            implementation("io.insert-koin:koin-test:3.5.3")
            implementation("io.ktor:ktor-client-core:2.3.8")
            implementation("io.ktor:ktor-client-content-negotiation:2.3.1")
            implementation("io.ktor:ktor-serialization-kotlinx-json:2.3.1")
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.2")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }

        androidMain.dependencies {
            implementation("io.ktor:ktor-client-okhttp:2.3.8")
            implementation("app.cash.sqldelight:android-driver:2.0.1")
        }

        iosMain.dependencies {
            implementation("io.ktor:ktor-client-darwin:2.3.8")
            implementation("app.cash.sqldelight:native-driver:2.0.1")
        }
    }
}

android {
    namespace = "com.quintallabs.randomdrink"
    compileSdk = 34
    defaultConfig {
        minSdk = 28
    }
}

sqldelight {
    databases {
        create("AppDatabase") {
            packageName.set("com.quintallabs.randomdrink")
            generateAsync.set(true)
        }
    }
}
