import me.omico.buildSrc.Versions.Androidx
import me.omico.buildSrc.Versions.Material
import me.omico.buildSrc.Versions.Project

plugins {
    id("com.android.library")
    id("kotlin-android")
}

android {
    defaultConfig {
        versionCode(Project.versionCode)
        versionName(Project.versionName)
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {
    compileOnly(Androidx.Appcompat.appcompat)
    compileOnly(Androidx.Core.coreKtx)
    compileOnly(Androidx.Fragment.fragmentKtx)
    compileOnly(Material.material)
}
