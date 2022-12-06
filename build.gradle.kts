import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.21"
}

apply(plugin = "kotlin")
apply(plugin = "application")
apply(plugin = "com.github.johnrengelman.shadow")
project.setProperty("mainClassName", "org.rowerowyrownik.RowerowyRownikOverlayKt")
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.http4k:http4k-core:${property("http4kVersion")}")
    implementation("org.http4k:http4k-client-apache:${property("http4kVersion")}")
    implementation("org.http4k:http4k-format-jackson:${property("http4kVersion")}")

    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:${property("kotlinVersion")}")

    testImplementation("org.junit.jupiter:junit-jupiter-api:${property("junitVersion")}")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:${property("junitVersion")}")
}

buildscript {
    repositories {
        mavenCentral()
        maven {
            url = uri("https://plugins.gradle.org/m2/")
        }
    }

    dependencies {
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:${property("kotlinVersion")}")
        classpath("gradle.plugin.com.github.johnrengelman:shadow:7.1.2")
    }
}

tasks {
    named<ShadowJar>("shadowJar") {

        archiveBaseName.set(project.name)
        mergeServiceFiles()

        manifest {
            attributes(mapOf("Main-Class" to "org.rowerowyrownik.RowerowyRownikOverlayKt"))
        }
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}
