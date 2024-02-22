plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
}

android {
    namespace = "com.quintallabs.randomdrink.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.quintallabs.randomdrink.android"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiler.get()
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
}

dependencies {
    implementation(project(":shared"))
    api("androidx.core:core-ktx:1.12.0")
    api("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    api("androidx.activity:activity-compose:1.8.2")
    api(platform("androidx.compose:compose-bom:2023.03.00"))
    api("androidx.compose.ui:ui")
    api("androidx.compose.ui:ui-graphics")
    api("androidx.compose.ui:ui-tooling-preview")
    api("androidx.compose.material3:material3")
    api("androidx.compose.material:material:1.6.1")
    api("androidx.navigation:navigation-compose:2.7.7")
    api("io.coil-kt:coil-compose:2.4.0")
    api("io.insert-koin:koin-core:3.5.3")
    api("io.insert-koin:koin-android:3.5.3")
    api("io.insert-koin:koin-android-compat:3.5.3")
    api("io.insert-koin:koin-androidx-workmanager:3.5.3")
    api("io.insert-koin:koin-androidx-navigation:3.5.3")
    api("io.insert-koin:koin-androidx-compose:3.5.3")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2023.03.00"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")

    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}