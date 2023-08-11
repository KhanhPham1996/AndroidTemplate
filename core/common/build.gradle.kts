import dependencies.Dependencies
import extensions.addTestsDependencies
import extensions.apiNavigation
import extensions.coroutines
import extensions.daggerHilt
import extensions.lifecycleScope

plugins {
    id(ScriptPlugins.android_library)
}

android{
    namespace = "com.hrs.mutiplemoduleapp.core.common"


    buildFeatures {
        dataBinding = false
        viewBinding = true
    }
}
dependencies {

    implementation(project(Modules.NETWORK))
    implementation(project(Modules.DOMAIN))
    implementation(Dependencies.ACTIVITY_KTX)
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.CONSTRAIN_LAYOUT)
    apiNavigation()
    lifecycleScope()
    addTestsDependencies()
    daggerHilt()
}