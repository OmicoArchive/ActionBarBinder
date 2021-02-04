import me.omico.buildSrc.Versions.Androidx
import me.omico.buildSrc.Versions.Material
import me.omico.buildSrc.Versions.Project

plugins {
    id("com.android.application")
    id("kotlin-android")
}

android {
    defaultConfig {
        applicationId("me.omico.binder.actionbar.sample")
        versionCode(Project.versionCode)
        versionName(Project.versionName)
    }
    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    implementation(Androidx.Appcompat.appcompat)
    implementation(Androidx.ConstraintLayout.constraintlayout)
    implementation(Androidx.Core.coreKtx)
    implementation(Androidx.Fragment.fragmentKtx)
    implementation(Androidx.Navigation.navigationFragmentKtx)
    implementation(Androidx.Navigation.navigationUiKtx)
    implementation(Material.material)
    implementation(project(":action-bar-binder"))
}
