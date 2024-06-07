import org.gradle.kotlin.dsl.accessors.runtime.addDependencyTo

rootProject.name = "graphql-vt-issue"

pluginManagement {
    repositories {
        mavenCentral()
        gradlePluginPortal()
    }
}

//buildscript {
//    repositories {
//        mavenCentral()
//        gradlePluginPortal()
//    }
//    dependencies {
//        classpath("com.google.cloud.tools:jib-spring-boot-extension-gradle:0.1.0")
//
//    }
//}