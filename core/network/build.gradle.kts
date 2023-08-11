
import dependencies.Dependencies
import extensions.daggerHilt

plugins {
    id(ScriptPlugins.android_library)
}

android {
    namespace = "com.hrs.mutiplemoduleapp.core.network"

}



dependencies {

    implementation(Dependencies.ACTIVITY_KTX)
    implementation(Dependencies.APPCOMPAT)
    implementation(Dependencies.MATERIAL)
    implementation(Dependencies.CONSTRAIN_LAYOUT)
    daggerHilt()
}
