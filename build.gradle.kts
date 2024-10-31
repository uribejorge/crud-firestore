// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
}

buildscript {
    dependencies {
        classpath("com.android.tools.build:gradle:8.0.2")
        classpath("com.google.gms:google-services:4.3.10")
    }
}

allprojects {
    // No agregar repositorios aquí, ya están configurados en settings.gradle.kts
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}