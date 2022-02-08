import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("com.android.application")
    id("kotlin-android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
    id("androidx.navigation.safeargs.kotlin")
}

android {
    compileSdk = 31

    defaultConfig {
        applicationId = "com.hsnozan.favoriterestaurants"
        minSdk = 24
        targetSdk = 31
        versionCode = 1
        versionName = "1.0.0"
        testInstrumentationRunner = "android.support.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            proguardFiles(
                    getDefaultProguardFile("proguard-android-optimize.txt"),
                    "proguard-rules.pro"
            )
        }
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    lint {
        disable("NullSafeMutableLiveData")
    }

    kapt {
        correctErrorTypes = true
    }

    tasks.withType(KotlinCompile::class).all {
        kotlinOptions {
            jvmTarget = "1.8"
            allWarningsAsErrors = true
            freeCompilerArgs = listOf("-Xopt-in=kotlin.RequiresOptIn")
        }
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }
}

dependencies {
    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.appCompat)
    implementation(Libraries.constraintLayout)
    implementation(Libraries.material)
    implementation(Libraries.hilt)
    implementation(Libraries.hiltViewModel)
    implementation(Libraries.glide)
    implementation(Libraries.viewModelKtx)
    implementation(Libraries.coroutinesAndroid)
    implementation(Libraries.fragmentKtx)
    implementation(Libraries.liveDataKtx)
    implementation(Libraries.navGraphKtx)
    implementation(Libraries.navUIKtx)
    implementation(Libraries.navFeatureKtx)
    implementation(Libraries.roomRuntime)
    implementation(Libraries.roomKtx)
    implementation(Libraries.gson)
    implementation("androidx.legacy:legacy-support-v4:1.0.0")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.0")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.4.0")

    kapt(Libraries.hiltCompiler)
    kapt(Libraries.hiltAndroidXCompiler)
    kapt(Libraries.glideCompiler)
    kapt(Libraries.dataBinding)
    kapt(Libraries.roomCompiler)

    testImplementation(Libraries.junit)
    testImplementation(Libraries.assertj)
    testImplementation(Libraries.coroutinesTest)
    testImplementation(Libraries.archCoreTesting)
    testImplementation(Libraries.mockitoInline)
    testImplementation(Libraries.mockitoKotlin)
    testImplementation(Libraries.mockitoCore)
    testImplementation(Libraries.mockitoAndroid)
    testImplementation(Libraries.mockitoNhaarmanKotlin)
    testImplementation(Libraries.roomTesting)
}