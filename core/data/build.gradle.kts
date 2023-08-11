import extensions.apiRetrofit
import dependencies.Dependencies
import extensions.daggerHilt

plugins {
    id(ScriptPlugins.android_library)

}

android {
    namespace = "com.hrs.mutiplemoduleapp.core.data"

}

dependencies {
    api(project(Modules.NETWORK))
    implementation(Dependencies.ACTIVITY_KTX)
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.CONSTRAIN_LAYOUT)
    apiRetrofit()
    daggerHilt()

}