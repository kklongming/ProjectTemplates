import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin application project to get you started.
 */

plugins {
    // Apply the Kotlin JVM plugin to add support for Kotlin on the JVM.
    id("org.jetbrains.kotlin.jvm").version("1.3.71")

    // Apply the application plugin to add support for building a CLI application.
    application
}

repositories {
    // Use jcenter for resolving your dependencies.
    // You can declare any Maven/Ivy/file repository here.
    maven {
        url = uri("http://kklongming.github.io/repository")
    }
    mavenLocal()
    mavenCentral()
    jcenter()
}

dependencies {
    // Use the Kotlin JDK 8 standard library.
    implementation(kotlin("stdlib-jdk8"))
    //implementation(kotlin("reflect"))
    //implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.3.4")
    implementation("com.github.kklongming:sz-crypto:3.0.0-dev")
    implementation("com.github.kklongming:sz-tools:3.0.0-dev")
    implementation("com.github.ajalt:clikt:2.3.0")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")

    configurations.all {
        this.exclude(group = "org.slf4j", module = "slf4j-log4j12")
    }
}

application {
    // Define the main class for the application.
    mainClassName = "com.sz.rsatool.AppKt"
}
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}