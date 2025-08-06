import com.vanniktech.maven.publish.SonatypeHost
import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.ExperimentalWasmDsl
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.targets.js.webpack.KotlinWebpackConfig

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.composeMultiplatform)
    alias(libs.plugins.composeCompiler)
    id("com.vanniktech.maven.publish") version "0.28.0"
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
        publishLibraryVariants("release", "debug")
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "kdeeplink-navigation-compose"
            isStatic = true
        }
    }
    
    jvm()
    
    @OptIn(ExperimentalWasmDsl::class)
    wasmJs {
        browser {
            val rootDirPath = project.rootDir.path
            val projectDirPath = project.projectDir.path
            commonWebpackConfig {
                devServer = (devServer ?: KotlinWebpackConfig.DevServer()).apply {
                    static = (static ?: mutableListOf()).apply {
                        // Serve sources to debug inside browser
                        add(rootDirPath)
                        add(projectDirPath)
                    }
                }
            }
        }
    }
    
    sourceSets {
        commonMain.dependencies {
            implementation(projects.kdeeplinkCore)
            implementation(libs.navigation.compose)
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "io.github.lucasferreiramachado.kdeeplink.navigation.compose"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}

mavenPublishing {
    // Define coordinates for the published artifact
    coordinates(
        groupId = "io.github.lucasferreiramachado",
        artifactId = "kdeeplink-navigation-compose",
        version = "0.2.0"
    )

    // Configure POM metadata for the published artifact
    pom {
        name.set("kdeeplink-navigation-compose")
        description.set("A lightweight, cross-platform deeplink library designed for Navigation Compose library.")
        inceptionYear.set("2025")
        url.set("https://github.com/lucasferreiramachado/kdeeplink")

        licenses {
            license {
                name.set("Apache License 2.0")
                url.set("https://github.com/lucasferreiramachado/kdeeplink/blob/main/LICENSE")
            }
        }

        // Specify developers information
        developers {
            developer {
                id.set("lucasferreiramachado")
                name.set("Lucas Ferreira Machado")
                url.set("https://github.com/lucasferreiramachado")
            }
        }

        // Specify SCM information
        scm {
            url.set("https://github.com/lucasferreiramachado/kdeeplink")
            connection.set("scm:git:git://github.com/lucasferreiramachado/kdeeplink.git")
        }
    }

    // Configure publishing to Maven Central
    publishToMavenCentral(SonatypeHost.CENTRAL_PORTAL)

    // Enable GPG signing for all publications
    signAllPublications()
}