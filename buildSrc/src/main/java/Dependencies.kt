
object Version {
    const val oneTapSignIn = "1.0.3"
    const val core = "1.7.0"
    const val lifecycle = "2.3.1"
    const val activityCompose = "1.3.1"
    const val composeUI = "1.3.1"
    const val material = "1.2.0"
    const val espresso = "3.5.1"
    const val junit = "4.13.2"
    const val testJunitExt = "1.1.5"
    const val fragmentsKtx = "1.5.0"

    // Navigation
    const val composeNavigation = "2.5.3"
    const val hiltComposeNavigation = "1.0.0"
    const val navVersion = "2.3.2"

    // Android X Versions
    const val appCompatActivity = "1.6.1"

    // Hilt Versions
    const val hilt = "2.44"
    const val hiltAndroidXCompiler = "1.0.0"

    // Accompanist Version
    const val accompanist = "0.18.0"

    // Coroutines
    const val coroutines = "1.6.0"
    const val coroutinePlayServices = "1.6.4"

    // ViewModel
    const val viewModel = "1.0.0-alpha"
    const val lifecycleViewModel = "1.0.0"
    const val lifecycleRuntimeCompose = "2.6.0"

    // Retrofit
    const val retrofit = "2.9.0"
    const val gsonConvertor = "2.9.0"
    const val okHttp = "4.9.0"
    const val scalerConvertor = "2.1.0"

    // Glide
    const val glide = "4.15.1"

    // Firebase
    const val bom = "32.1.0"
    const val playServicesAuth = "20.5.0"
    const val authKtx = "22.0.0"
    const val playServices = "4.3.15"

    // Testing
    const val testRunner = "1.4.0"
    const val testRules = "1.4.0"
    const val androidxTest = "1.1.5"

}

object Deps {
    const val core = "androidx.core:core-ktx:${Version.core}"
    const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycle}"
    const val activityCompose = "androidx.activity:activity-compose:${Version.activityCompose}"
    const val composeUi = "androidx.compose.ui:ui:${Version.composeUI}"
    const val composeToolingPreview = "androidx.compose.ui:ui-tooling-preview:${Version.composeUI}"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Version.composeUI}"
    const val composeUiManifest = "androidx.compose.ui:ui-test-manifest:${Version.composeUI}"
    const val material = "androidx.compose.material:material:${Version.material}"
    const val accompanist = "com.google.accompanist:accompanist-systemuicontroller:${Version.accompanist}"
    const val fragmentsKtx = "androidx.fragment:fragment-ktx:${Version.fragmentsKtx}"
    const val glide = "com.github.bumptech.glide:glide:${Version.glide}"
}

object Coroutines {
    const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"
    const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"
    const val playServices = "org.jetbrains.kotlinx:kotlinx-coroutines-play-services:${Version.coroutinePlayServices}"
}

object Navigation {
    const val navigationUi = "androidx.navigation:navigation-ui-ktx:${Version.navVersion}"
    const val navigation = "androidx.navigation:navigation-fragment-ktx:${Version.navVersion}"
    const val safeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Version.navVersion}"
}

object AndroidX {
    const val appCompatActivity = "androidx.appcompat:appcompat:${Version.appCompatActivity}"
}

object Compose {
    const val navigation = "androidx.navigation:navigation-compose:${Version.composeNavigation}"
    const val lifecycleRuntimeCompose = "androidx.lifecycle:lifecycle-runtime-compose:${Version.lifecycleRuntimeCompose}"

}

object Retrofit {
    const val retrofit = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
    const val gsonConvertor = "com.squareup.retrofit2:converter-gson:${Version.gsonConvertor}"
    const val okHttp = "com.squareup.okhttp3:okhttp:${Version.okHttp}"
    const val scalersConvertors =
        "com.squareup.retrofit2:converter-scalars:${Version.scalerConvertor}"
}

object Dagger {
    const val hilt = "com.google.dagger:hilt-android:${Version.hilt}"
    const val compiler = "com.google.dagger:hilt-compiler:${Version.hilt}"
    const val navigation = "androidx.hilt:hilt-navigation-compose:${Version.hiltComposeNavigation}"

}

object Firebase{
    const val oneTapSignIn = "com.github.stevdza-san:OneTapCompose:${Version.oneTapSignIn}"
    const val bom = "com.google.firebase:firebase-bom:${Version.bom}"
    const val authKtx = "com.google.firebase:firebase-auth-ktx:${Version.authKtx}"
    const val playServices = "com.google.gms:google-services:${Version.playServices}"
    const val playServicesAuth = "com.google.android.gms:play-services-auth:${Version.playServicesAuth}"
    const val firestore = "com.google.firebase:firebase-firestore-ktx"
}

object ComposeDependencies {
    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-compose:${Version.lifecycleViewModel}"
}

object Testing {
    const val composeUiJunitTest = "androidx.compose.ui:ui-test-junit4:${Version.composeUI}"
    const val junit4 = "junit:junit:${Version.junit}"
    const val testExtJUnit = "androidx.test.ext:junit:${Version.testJunitExt}"
    const val espresso = "androidx.test.espresso:espresso-core:${Version.espresso}"
    const val testRunner = "androidx.test:runner:${Version.testRunner}"
    const val testRules = "androidx.test:rules:${Version.testRules}"
    const val androidxTest = "androidx.test.ext:junit:${Version.androidxTest}"
}


