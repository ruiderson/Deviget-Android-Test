buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(Dependencies.Plugins.ANDROID)
        classpath(Dependencies.Plugins.KOTLIN)
        classpath(Dependencies.Plugins.KOTLIN_SERIALIZATION)
    }
}

subprojects {
    repositories {
        google()
        mavenCentral()
    }
}

tasks.register("clean", Delete::class){
    delete(rootProject.buildDir)
}
