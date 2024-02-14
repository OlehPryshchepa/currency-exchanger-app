plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    //id("com.google.dagger.hilt.android")
    //id("com.google.devtools.ksp")
}

android {
    namespace = "com.example.currency_changer_app"
    compileSdk = 34

    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    defaultConfig {
        applicationId = "com.example.currency_changer_app"
        minSdk = 24
        targetSdk = 33
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
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
    kotlin {
       jvmToolchain(17)
    }
    kotlinOptions {
        jvmTarget = "17"
    }
}

dependencies {
    implementation("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation("androidx.navigation:navigation-ui-ktx:2.7.7")
    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
//
    val room_version = "2.6.1"
//
    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    annotationProcessor("androidx.room:room-compiler:$room_version")
//
//    // To use Kotlin annotation processing tool (kapt)
    kapt("androidx.room:room-compiler:$room_version")
//    // To use Kotlin Symbol Processing (KSP)
//    ksp "androidx.room:room-compiler:$room_version"
//
//    // optional - RxJava2 support for Room
//    implementation "androidx.room:room-rxjava2:$room_version"
//
//    // optional - RxJava3 support for Room
//    implementation "androidx.room:room-rxjava3:$room_version"
//
//    // optional - Guava support for Room, including Optional and ListenableFuture
//    implementation "androidx.room:room-guava:$room_version"
//
//    // optional - Test helpers
//    testImplementation "androidx.room:room-testing:$room_version"
//
//    // optional - Paging 3 Integration
//    implementation "androidx.room:room-paging:$room_version"

    implementation("com.android.volley:volley:1.2.1")
    //implementation("com.google.dagger:hilt-android:2.48")
    //ksp("com.google.dagger:hilt-compiler:2.48")
}