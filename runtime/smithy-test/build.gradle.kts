/*
 * Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
 * SPDX-License-Identifier: Apache-2.0
 */

description = "Test utilities for generated Smithy services"
extra["displayName"] = "Smithy :: Kotlin :: Test"
extra["moduleName"] = "aws.smithy.kotlin.runtime.smithy.test"

val coroutinesVersion: String by project
val kotlinVersion: String by project
val kotlin_version: String? by project
val finalKotlinVersion = kotlin_version ?: kotlinVersion
val kotlinxSerializationVersion: String by project

kotlin {
    sourceSets {
        commonMain {
            dependencies {
                api(project(":runtime:protocol:http-client"))
                implementation(project(":runtime:protocol:http-test"))

                implementation(project(":runtime:serde:serde-xml"))

                implementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:$coroutinesVersion")
                implementation("org.jetbrains.kotlin:kotlin-test-common:$finalKotlinVersion")

                // kotlinx-serialization::JsonElement allows comparing arbitrary JSON docs for equality
                implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:$kotlinxSerializationVersion")
            }
        }

        jvmMain {
            dependencies {
                implementation("org.jetbrains.kotlin:kotlin-test:$finalKotlinVersion")
            }
        }

        all {
            languageSettings.optIn("aws.smithy.kotlin.runtime.InternalApi")
        }
    }
}
