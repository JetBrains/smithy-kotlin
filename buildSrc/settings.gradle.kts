/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

rootProject.name = "buildSrc"

pluginManagement {
    repositories {
        val kotlin_repo_url: String? by settings
        kotlin_repo_url?.let { maven(it) }
        gradlePluginPortal()
        mavenCentral()
    }
    val kotlinVersion = providers.gradleProperty("kotlin_version").get()
    resolutionStrategy {
        eachPlugin {
            if (requested.id.id.startsWith("org.jetbrains.kotlin"))
                useVersion(kotlinVersion)
        }
    }
}

dependencyResolutionManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        val kotlin_repo_url: String? by settings
        kotlin_repo_url?.let { maven(it) }
    }

    versionCatalogs {
        create("libs") {
            from(files("../gradle/libs.versions.toml"))
            val kotlinVersionOverride = providers.gradleProperty("kotlin_version")
            if (kotlinVersionOverride.isPresent) {
                println("[Smithy buildSrc] Overriding kotlin-version to ${kotlinVersionOverride.get()}")
                version("kotlin-version", kotlinVersionOverride.get())
            }
        }
    }
}