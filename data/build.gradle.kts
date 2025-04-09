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
    implementation("io.netty:netty-resolver-dns-native-macos:4.2.0.Final:osx-aarch_64")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}