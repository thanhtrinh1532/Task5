plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.sqlite"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.sqlite"
        minSdk = 28
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    // Thêm phụ thuộc cho Room
    implementation ("androidx.room:room-runtime:2.5.0") // Đảm bảo là phiên bản mới nhất
    implementation ("androidx.room:room-ktx:2.5.0")
    implementation(libs.room.common) // Optional nếu bạn muốn sử dụng Kotlin Coroutines
    annotationProcessor("androidx.room:room-compiler:2.5.0")

    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}