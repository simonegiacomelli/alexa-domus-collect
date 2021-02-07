import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.4.10"
}

group = "pro.jako"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.jetbrains.lets-plot-kotlin:lets-plot-kotlin-api:1.2.0")
    implementation("org.jetbrains.lets-plot-kotlin:lets-plot-kotlin-api-kernel:1.2.0")
    api( "org.jetbrains.lets-plot:lets-plot-common:1.2.0")
    api(  "org.jetbrains.lets-plot-kotlin:lets-plot-kotlin-api:1.2.0")
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.create<JavaExec>("execute") {
    group = "application"
    classpath = sourceSets.main.get().runtimeClasspath

    main = "JvmMainKt"

    standardInput = System.`in`
//    standardOutput = System.out
//    errorOutput = System.err
}