plugins {
    kotlin("jvm")
}

group = "portable.chlwhdtn"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.vertx:vertx-web:4.5.14")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}