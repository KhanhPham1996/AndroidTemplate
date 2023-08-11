repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
}

plugins {
    `kotlin-dsl`
    `kotlin-dsl-precompiled-script-plugins`
}

object PluginsVersions {
    const val GRADLE_ANDROID = "7.4.2"
    const val KOTLIN = "1.8.21"
    const val HILT = "2.47"
    const val NAVIGATION = "2.6.0"
}
dependencies {
    // android gradle plugin, required by custom plugin
    implementation("com.android.tools.build:gradle:${PluginsVersions.GRADLE_ANDROID}")
    // kotlin plugin, required by custom plugin
    implementation(kotlin("gradle-plugin", PluginsVersions.KOTLIN))
    implementation("com.google.dagger:hilt-android-gradle-plugin:${PluginsVersions.HILT}")
    implementation("androidx.navigation:navigation-safe-args-gradle-plugin:${PluginsVersions.NAVIGATION}")

}