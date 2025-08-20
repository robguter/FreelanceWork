plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.sisterag.FreelanceWork"
    compileSdk = 36

    defaultConfig {
        applicationId = "com.sisterag.FreelanceWork"
        minSdk = 24
        targetSdk = 36
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
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        viewBinding = true
        dataBinding = true
    }

    packaging {
        resources {
            excludes += "**/*"
        }
    }
}

dependencies {

    val lcrk = "2.9.2"
    val csr = "3.0.0"
    val csoli = "5.1.0"
    val kCA = "1.10.2"
    val kCC = "1.10.2"
    val kSl = "2.2.0"
    val kRf = "2.1.20"
    val pSAI = "18.2.0"
    val pSM = "19.2.0"

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.constraintlayout)

    // retrofit
    implementation("com.squareup.retrofit2:retrofit:$csr")
    // gson converter
    implementation("com.squareup.retrofit2:converter-gson:$csr")
    // okhttp3
    implementation("com.squareup.okhttp3:logging-interceptor:$csoli")
    implementation("com.squareup.okhttp3:okhttp:$csoli")
    //coroutines
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:$kCA")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:$kCC")
    //Room
    implementation("androidx.room:room-runtime:2.7.2")
    kapt("androidx.room:room-compiler:2.7.2")

    // Gson (si no usas el converter de Retrofit)
    //implementation("com.google.code.gson:gson:$gsn")

    implementation(libs.glide)
    annotationProcessor(libs.compiler)

    implementation("org.jetbrains.kotlin:kotlin-stdlib:$kSl")
    implementation("org.jetbrains.kotlin:kotlin-reflect:$kRf")
    implementation("com.google.android.gms:play-services-analytics-impl:$pSAI")
    implementation("com.google.android.gms:play-services-maps:$pSM")
    implementation("androidx.datastore:datastore-preferences:1.1.7")
    implementation("io.coil-kt:coil:2.7.0")
    implementation("com.karumi:dexter:6.2.3")

    implementation("androidx.lifecycle:lifecycle-extensions:2.2.0")
    implementation(libs.androidx.lifecycle.livedata.ktx)
    implementation(libs.androidx.lifecycle.viewmodel.ktx)
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:${lcrk}")

    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.androidx.navigation.fragment.ktx)
    implementation(libs.androidx.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}