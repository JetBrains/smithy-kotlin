/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

description = "Internal test utilities"

val coroutinesVersion: String by project
val kotlinVersion: String by project
val kotlin_version: String? by project
val finalKotlinVersion = kotlin_version ?: kotlinVersion
val junitVersion: String by project

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-test-common:$finalKotlinVersion")
                implementation("org.jetbrains.kotlin:kotlin-test-annotations-common:$finalKotlinVersion")
            }
        }
        jvmMain {
            dependencies {
                api("org.junit.jupiter:junit-jupiter:$junitVersion")
                implementation("org.jetbrains.kotlin:kotlin-test:$finalKotlinVersion")
                api("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
            }
        }
    }
}
