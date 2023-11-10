plugins {
    id("java-library")
    id("org.jetbrains.kotlin.jvm")

    id("kotlin-kapt")
}

java {
    sourceCompatibility = JavaVersion.VERSION_17
    targetCompatibility = JavaVersion.VERSION_17
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    val hilt_version = "2.48"
    implementation ("com.google.dagger:hilt-core:$hilt_version")
    kapt ("com.google.dagger:hilt-android-compiler:$hilt_version")
}

kapt {
    correctErrorTypes = true
}