import extensions.apiNavigation
import extensions.daggerHilt
import dependencies.Dependencies
import extensions.addTestsDependencies
import extensions.coil
import extensions.lifecycleScope

plugins {
    id(ScriptPlugins.android_app)
}

android {
    namespace = BuildAndroidConfig.NAMESPACE
    compileSdk = BuildAndroidConfig.COMPILE_SDK_VERSION

    defaultConfig {
        applicationId = BuildAndroidConfig.APPLICATION_ID
        minSdk = BuildAndroidConfig.MIN_SDK_VERSION
        targetSdk = BuildAndroidConfig.TARGET_SDK_VERSION
        versionCode = BuildAndroidConfig.VERSION_CODE
        versionName = BuildAndroidConfig.VERSION_NAME
    }
    kapt {
        arguments {
            arg("room.schemaLocation", "$projectDir/schemas")
        }
    }


}

dependencies {
    api(project(Modules.COMMON))
    api(project(Modules.DOMAIN))
    api(project(Modules.DATA))
    implementation(Dependencies.ACTIVITY_KTX)
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.MULTIDEX)
    implementation(Dependencies.CONSTRAIN_LAYOUT)
    daggerHilt()
    addTestsDependencies()

    lifecycleScope()
    coil()

}