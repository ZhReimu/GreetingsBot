import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.6.10"
    application
}

group = "me.administrator"
version = "1.0-SNAPSHOT"

repositories {
    maven("https://maven.aliyun.com/repository/central")
    mavenCentral()
}

dependencies {
    // https://mvnrepository.com/artifact/org.apache.commons/commons-configuration2
    implementation("org.apache.commons:commons-configuration2:2.7")
    // https://mvnrepository.com/artifact/org.eclipse.emf/org.eclipse.emf.ecore
    implementation("org.eclipse.emf:org.eclipse.emf.ecore:2.25.0")
    // https://mvnrepository.com/artifact/fr.inria.atlanmod.commons/commons
    implementation("fr.inria.atlanmod.commons:commons:1.0.1")
    // https://mvnrepository.com/artifact/fr.inria.atlanmod.commons/commons-core
    implementation("fr.inria.atlanmod.commons:commons-core:1.0.1")
    // https://mvnrepository.com/artifact/org.apache.httpcomponents/httpclient
    implementation("org.apache.httpcomponents:httpclient:4.5.13")
    // https://mvnrepository.com/artifact/com.google.code.gson/gson
    implementation("com.google.code.gson:gson:2.8.9")
    // https://mvnrepository.com/artifact/org.mapdb/mapdb
    implementation("org.mapdb:mapdb:3.0.8")
    // https://mvnrepository.com/artifact/org.slf4j/slf4j-simple
    implementation("org.slf4j:slf4j-simple:1.7.32")
    // https://mvnrepository.com/artifact/com.corundumstudio.socketio/netty-socketio
    implementation("com.corundumstudio.socketio:netty-socketio:1.7.19")

    implementation(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

application {
    mainClass.set("MainKt")
}