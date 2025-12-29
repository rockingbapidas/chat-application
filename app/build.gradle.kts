plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.kapt)
    alias(libs.plugins.daggerHiltAndroidPlugin)
    alias(libs.plugins.googleServices)
    alias(libs.plugins.ksp)
    alias(libs.plugins.kotlinPluginCompose)
    alias(libs.plugins.kotlinSerialization)
}

android {
    namespace = "com.example.whatsappsample"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.example.whatsappsample"
        minSdk = 24
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }

    buildFeatures {
        compose = true
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

// Configure Kotlin Compile Tasks
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
    compilerOptions {
        jvmTarget.set(org.jetbrains.kotlin.gradle.dsl.JvmTarget.JVM_17)
    }
}

dependencies {
    // Firebase
    implementation(libs.firebaseAuthKtx)
    implementation(libs.firebaseFirestoreKtx)
    implementation(libs.firebaseStorageKtx)
    implementation(libs.coroutinesPlayServices)

    // Compose
    implementation(platform(libs.composeBom))
    implementation(libs.composeUi)
    implementation(libs.composeUiGraphics)
    implementation(libs.composeMaterial3)
    implementation(libs.composeMaterialIconsExtended)
    implementation(libs.activityCompose)
    implementation(libs.navigationCompose)
    implementation(libs.lifecycleViewmodelCompose)
    implementation(libs.hiltNavigationCompose)

    // Room
    implementation(libs.roomRuntime)
    implementation(libs.roomKtx)
    ksp(libs.roomCompiler)
    
    // Gson
    implementation(libs.gson)

    // WorkManager
    implementation(libs.workRuntimeKtx)

    // Hilt
    implementation(libs.hiltAndroid)
    kapt(libs.hiltAndroidCompiler)
    implementation(libs.hiltWork)
    kapt(libs.hiltCompiler)

    // Ktor for WebSocket
    implementation(libs.ktorClientCore)
    implementation(libs.ktorClientCio)
    implementation(libs.ktorClientWebsockets)
    implementation(libs.ktorClientContentNegotiation)
    implementation(libs.ktorSerializationKotlinxJson)
    
    // Kotlinx Serialization
    implementation(libs.kotlinxSerializationJson)

    // Smack for XMPP
    implementation(libs.smackAndroid)
    implementation(libs.smackTcp)
    implementation(libs.smackIm)
    implementation(libs.smackExtensions)

    // Testing
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidxTestExtJunit)
    androidTestImplementation(libs.espressoCore)
    androidTestImplementation(platform(libs.composeBom))
    androidTestImplementation(libs.composeUiTestJunit4)
    debugImplementation(libs.composeUiTooling)
    debugImplementation(libs.composeUiTestManifest)
}

configurations {
    all {
        exclude(group = "xpp3", module = "xpp3")
    }
}
