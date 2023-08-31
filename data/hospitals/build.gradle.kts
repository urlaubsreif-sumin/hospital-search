import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.library")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.sumin.data.hospitals"
    compileSdk = 33

    defaultConfig {
        minSdk = 24
        targetSdk = 33

        buildConfigField("String", "API_KEY", getApiKey("api_key"))
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildFeatures {
        buildConfig = true
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
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = JavaVersion.VERSION_17.toString()
    }
}


fun getApiKey(propertyKey: String): String {
    return gradleLocalProperties(rootDir).getProperty(propertyKey)
}

dependencies {
    /* Modules */
    implementation(project(mapOf("path" to ":domain:hospital-list")))
    implementation(project(mapOf("path" to ":core:database")))

    
    implementation("androidx.core:core-ktx:1.10.1")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.9.0")

    implementation("com.google.devtools.ksp:symbol-processing-api:1.9.0-1.0.11")

    val retrofit_version = "2.9.0"
    implementation("com.squareup.retrofit2:converter-gson:$retrofit_version")
    implementation("com.squareup.retrofit2:retrofit:$retrofit_version")

    /* tikXML */
    val tikxml_version = "0.8.13"
    implementation("com.tickaroo.tikxml:annotation:$tikxml_version")
    implementation("com.tickaroo.tikxml:core:$tikxml_version")
    implementation("com.tickaroo.tikxml:retrofit-converter:$tikxml_version")
    kapt("com.tickaroo.tikxml:processor:$tikxml_version")

    /* Paging3 */
    val paging_version = "3.1.1"
    implementation("androidx.paging:paging-runtime:$paging_version")

    /* Room */
    val room_version = "2.5.0"
    implementation("androidx.room:room-runtime:$room_version")
    implementation("androidx.room:room-ktx:$room_version")
    kapt("androidx.room:room-compiler:$room_version")

    val hilt_version = "2.44"
    implementation("com.google.dagger:hilt-android:$hilt_version")
    kapt("com.google.dagger:hilt-android-compiler:$hilt_version")

    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
}

kapt{
    correctErrorTypes = true
}