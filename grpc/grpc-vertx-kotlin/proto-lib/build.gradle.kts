import com.google.protobuf.gradle.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

/*
 * This file was generated by the Gradle 'init' task.
 *
 * This generated file contains a sample Kotlin library project to get you started.
 */

plugins {
    id("java")
    // Apply the Kotlin JVM plugin to add support for Kotlin on the JVM.
    id("org.jetbrains.kotlin.jvm")

    // Apply the Kotlin JVM plugin to add support for Kotlin on the JVM.
    id("maven-publish")

    id("com.google.protobuf").version("0.8.10")
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

val grpcVersion = "1.20.0"

dependencies {
    // Use the Kotlin JDK 8 standard library.
    implementation(kotlin("stdlib-jdk8"))
    //implementation(kotlin("reflect"))
    //implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.2.0")

    // android gradle依赖：implementation 和compile的区别
    // 参考: https://www.jianshu.com/p/f34c179bc9d0 根据需要选择使用不同的依赖设定方式

    // grpc for java, ref: https://github.com/grpc/grpc-java
    api("com.google.protobuf:protobuf-java:3.6.1")
    api("io.grpc:grpc-stub:$grpcVersion")
    api("io.grpc:grpc-protobuf:$grpcVersion")
    api("io.vertx:vertx-grpc:3.8.3")

    // Use the Kotlin test library.
    testImplementation("org.jetbrains.kotlin:kotlin-test")

    // Use the Kotlin JUnit integration.
    testImplementation("org.jetbrains.kotlin:kotlin-test-junit")
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    jvmTarget = "1.8"
}
val compileTestKotlin: KotlinCompile by tasks
compileTestKotlin.kotlinOptions {
    jvmTarget = "1.8"
}

tasks.register<Jar>("sourcesJar") {
    from(sourceSets.main.get().allSource)
    archiveClassifier.set("sources")
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            groupId = "com.github.kklongming"

            from(components["java"])
            artifact(tasks["sourcesJar"])
        }
    }
}

protobuf {
//    generatedFilesBaseDir = "$projectDir/src/generated"
    protoc {
        // The artifact spec for the Protobuf Compiler
        artifact = "com.google.protobuf:protoc:3.7.1"
    }
    plugins {
        // Optional: an artifact spec for a protoc plugin, with "grpc" as
        // the identifier, which can be referred to in the "plugins"
        // container of the "generateProtoTasks" closure.
        id("grpc") {
            artifact = "io.vertx:protoc-gen-grpc-java:$grpcVersion"
        }
    }
    generateProtoTasks {
        ofSourceSet("main").forEach {
            it.plugins {
                // Apply the "grpc" plugin whose spec is defined above, without options.
                id("grpc")
            }
        }
    }
}

sourceSets {
    main {
        java.srcDirs("${buildDir.absolutePath}/generated/source/proto/main/java", "${buildDir.absolutePath}/generated/source/proto/main/grpc")
        println("${buildDir.absolutePath}/generated/source/proto/main")
//        java.srcDirs.add(File("${buildDir.absolutePath}/generated/source/proto/main/java"))
//        java.srcDirs.add(File("${buildDir.absolutePath}/generated/source/proto/main/grpc"))
    }
}