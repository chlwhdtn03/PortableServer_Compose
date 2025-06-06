import org.jetbrains.compose.desktop.application.dsl.TargetFormat

plugins {
    kotlin("jvm")
    id("org.jetbrains.compose") apply false
    id("org.jetbrains.kotlin.plugin.compose") apply false
}

allprojects {
    group = "portable.chlwhdtn"
    version = "1.0-SNAPSHOT"

}

repositories {
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
    google()
}
    
dependencies {
    // Note, if you develop a library, you should use compose.desktop.common.
    // compose.desktop.currentOs should be used in launcher-sourceSet
    // (in a separate module for demo project and in testMain).
    // With compose.desktop.common you will also lose @Preview functionality
    //implementation(compose.desktop.currentOs)
    //implementation(":data")
}

//compose.desktop {
//    application {
//        mainClass = "MainKt"
//
//        nativeDistributions {
//
//            targetFormats(TargetFormat.Dmg, TargetFormat.Exe, TargetFormat.Deb)
//            packageName = "PortableServer_Compose"
//            packageVersion = "1.0.0"
//            macOS {
//                iconFile.set(project.file("idk.jpeg"))
//            }
//        }
//    }
//}
