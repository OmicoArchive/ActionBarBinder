import com.android.build.gradle.BaseExtension
import com.github.benmanes.gradle.versions.updates.DependencyUpdatesTask
import me.omico.buildSrc.Versions
import me.omico.buildSrc.configureAndroidLibraryPublish
import me.omico.buildSrc.forEachAndroidLibraryProject
import me.omico.buildSrc.forEachAndroidProject
import me.omico.buildSrc.registerAndroidSourcesJarTask
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories {
        google()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.1.2")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.4.30")
    }
}

allprojects {
    repositories {
        google()
        mavenCentral()
        jcenter()
    }
    configureDependencyUpdates()
    configureSpotless()
}

forEachAndroidProject { configureCommonAndroidConfiguration() }
forEachAndroidLibraryProject {
    registerAndroidSourcesJarTask()
    configureAndroidLibraryPublish(Versions.Project.versionName)
}

configureEachKotlinJvmTarget("1.8")

plugins {
    id("com.github.ben-manes.versions") version "0.36.0"
    id("com.diffplug.spotless") version "5.9.0"
}

fun Project.configureDependencyUpdates() {
    apply(plugin = "com.github.ben-manes.versions")
    tasks.named("dependencyUpdates", DependencyUpdatesTask::class) {
        outputFormatter = "plain"
    }
}

fun Project.configureSpotless() {
    apply(plugin = "com.diffplug.spotless")
    spotless {
        kotlin {
            target("src/**/*.kt")
            ktlint("0.40.0")
            licenseHeaderFile(rootProject.file("spotless/copyright"))
            trimTrailingWhitespace()
            endWithNewline()
        }
        format("xml") {
            target("src/**/*.xml")
            endWithNewline()
        }
    }
}

fun Project.configureCommonAndroidConfiguration() {
    configure<BaseExtension> {
        compileSdkVersion(30)
        buildToolsVersion("30.0.3")
        defaultConfig {
            minSdkVersion(24)
            targetSdkVersion(30)
        }
        compileOptions {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    }
}

fun Project.configureEachKotlinJvmTarget(version: String) {
    tasks.withType<KotlinCompile>().configureEach {
        kotlinOptions.jvmTarget = version
    }
}
