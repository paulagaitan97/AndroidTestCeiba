// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {

    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        // Build es un archivo de dependencias creado en el paso 3-> a
        classpath (Build.androidBuildTools)
        classpath (Build.hiltAndroidGradlePlugin)
        classpath (Build.kotlinGradlePlugin)
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.6.10")

    }
}


tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}