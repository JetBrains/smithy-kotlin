/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

buildscript {
    val atomicFuVersion: String by project
    val kotlin_repo_url: String? by project
    logger.info("[COMMUNITY_PROJECT_BUILD] kotlin repository url: $kotlin_repo_url")
    repositories {
        mavenCentral()
        kotlin_repo_url?.also { maven(it) }
    }

    dependencies {
        classpath("org.jetbrains.kotlinx:atomicfu-gradle-plugin:$atomicFuVersion")
    }
}

apply(plugin = "kotlinx-atomicfu")

description = "Utilities for working with AWS CRT Kotlin"
extra["displayName"] = "Smithy :: Kotlin :: CRT :: Util"
extra["moduleName"] = "aws.smithy.kotlin.runtime.crt"

val atomicFuVersion: String by project
val coroutinesVersion: String by project
val crtKotlinVersion: String by project

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":runtime:runtime-core"))
                api("aws.sdk.kotlin.crt:aws-crt-kotlin:$crtKotlinVersion")
                api(project(":runtime:protocol:http"))
                implementation("org.jetbrains.kotlinx:atomicfu:$atomicFuVersion")
            }
        }
        commonTest {
            dependencies {
                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
            }
        }

        all {
            languageSettings.optIn("aws.smithy.kotlin.runtime.InternalApi")
        }
    }
}
