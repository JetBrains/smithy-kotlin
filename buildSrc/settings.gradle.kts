/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

rootProject.name = "buildSrc"

pluginManagement {
    repositories {
        val kotlinRepoUrl = providers.gradleProperty("kotlin_repo_url").orNull
        val kotlinVersion = providers.gradleProperty("kotlin_version").orNull
        if (!kotlinRepoUrl.isNullOrEmpty() && !kotlinVersion.isNullOrEmpty()) {
            exclusiveContent {
                forRepository {
                    maven(kotlinRepoUrl)
                }
                filter {
                    includeVersionByRegex("org.jetbrains.kotlin.*", ".*", kotlinVersion)
                }
            }
        }
        gradlePluginPortal()
        mavenCentral()
    }
    val kotlinVersionProvider = providers.gradleProperty("kotlin_version")
    if (kotlinVersionProvider.isPresent) {
        val kotlinVersion = kotlinVersionProvider.get()
        resolutionStrategy {
            eachPlugin {
                if (requested.id.id.startsWith("org.jetbrains.kotlin"))
                    useVersion(kotlinVersion)
            }
        }
    }
}

dependencyResolutionManagement {
    repositories {
        gradlePluginPortal()
        mavenCentral()
        val kotlinRepoUrl = providers.gradleProperty("kotlin_repo_url").orNull
        val kotlinVersion = providers.gradleProperty("kotlin_version").orNull
        if (!kotlinRepoUrl.isNullOrEmpty() && !kotlinVersion.isNullOrEmpty()) {
            exclusiveContent {
                forRepository {
                    maven(kotlinRepoUrl)
                }
                filter {
                    includeVersionByRegex("org.jetbrains.kotlin.*", ".*", kotlinVersion)
                }
            }
        }
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