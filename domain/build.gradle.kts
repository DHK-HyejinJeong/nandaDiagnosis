plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
    id("kotlin-parcelize")
    id("dagger.hilt.android.plugin")
}

listOf(
        "android.gradle",
).forEach { file ->
    apply(from = "${rootDir}/gradle/${file}")
}

dependencies {
    implementation(project(":data"))
    implementation(project(":common"))

    implementation(AndroidX.core_ktx)
    implementation(Hilt.android)
    kapt(Hilt.hilt_compiler)

    implementation(SquareUp.timber)
    implementation(Coroutines.android)

    implementation(Kotlin.stdlibJvm)
    testImplementation(AppTest.junit)
    androidTestImplementation(AppTest.androidJunit)
    androidTestImplementation(AppTest.espressoCore)
    androidTestImplementation(Coroutines.test)
}

kapt {
    useBuildCache = true
}