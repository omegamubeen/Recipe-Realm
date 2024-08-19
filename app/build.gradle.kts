plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "com.app.reciperealm"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.app.reciperealm"
        minSdk = 21
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        buildConfigField ("String", "BASE_URL", "\"https://www.themealdb.com/api/json/v1/\"")
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildFeatures {
        viewBinding = true
        buildConfig = true

    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Navigation
    //def navVersion = ("2.5.3")
    implementation("androidx.navigation:navigation-fragment-ktx:2.5.3")
    implementation("androidx.navigation:navigation-ui-ktx:2.5.3")

    //TabLayout & Viewpager
    implementation("androidx.viewpager2:viewpager2:1.0.0")

    //Image processing
    implementation("com.github.bumptech.glide:glide:4.14.2")

    //Sizing
    implementation("com.intuit.sdp:sdp-android:1.1.0")

    //Lifecycle
    //def lifecycleVersion = ("2.5.1")
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.5.1")
    implementation("androidx.lifecycle:lifecycle-viewmodel-ktx:2.5.1")

    // Retrofit
    //def retrofitVersion = ("2.9.0")
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // Network Requests interceptor
    implementation("com.squareup.okhttp3:logging-interceptor:5.0.0-alpha.2")

    // Koin DI
    //def koin_version = ("3.2.2")
    implementation("io.insert-koin:koin-core:3.2.2")
    implementation("io.insert-koin:koin-android:3.3.0")

    implementation("com.skyfishjy.ripplebackground:library:1.0.1")

    // Room
    //def room_version = "2.5.1"
    implementation("androidx.room:room-ktx:2.5.1")
    annotationProcessor("androidx.room:room-compiler:2.5.1")

}