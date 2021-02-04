/*
 * Copyright 2021 Omico
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package me.omico.buildSrc

import org.gradle.api.Project
import org.gradle.api.publish.PublishingExtension
import org.gradle.api.publish.maven.MavenPublication
import org.gradle.kotlin.dsl.apply
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.create
import org.gradle.kotlin.dsl.get
import org.gradle.plugins.signing.SigningExtension
import java.util.Properties

fun Project.configureAndroidLibraryPublish(versionName: String) {
    apply(plugin = "org.gradle.maven-publish")
    apply(plugin = "org.gradle.signing")
    afterEvaluate {
        configure<PublishingExtension> {
            publications {
                create<MavenPublication>("maven") {
                    groupId = getProperty<String>("POM_GROUP_ID")
                    artifactId = getProperty<String>("POM_ARTIFACT_ID")
                    version = versionName
                    artifact(tasks["androidSourcesJar"])
                    artifact("$buildDir/outputs/aar/${project.name}-release.aar")
                    pom {
                        name.set(getProperty<String>("POM_NAME"))
                        description.set(getProperty<String>("POM_DESCRIPTION"))
                        url.set(getProperty<String>("POM_URL"))
                        licenses {
                            license {
                                name.set(getProperty<String>("POM_LICENCE_NAME"))
                                url.set(getProperty<String>("POM_LICENCE_URL"))
                            }
                        }
                        developers {
                            developer {
                                id.set(getProperty<String>("POM_DEVELOPER_ID"))
                                name.set(getProperty<String>("POM_DEVELOPER_NAME"))
                            }
                        }
                        scm {
                            connection.set(getProperty<String>("POM_SCM_CONNECTION"))
                            developerConnection.set(getProperty<String>("POM_SCM_DEV_CONNECTION"))
                            url.set(getProperty<String>("POM_SCM_URL"))
                        }
                    }
                }
            }
            repositories {
                maven {
                    credentials {
                        username = localProperties.getProperty("NEXUS_USERNAME")
                        password = localProperties.getProperty("NEXUS_PASSWORD")
                    }
                    val name = when {
                        isSnapshot(versionName) -> "NEXUS_PUBLISH_SNAPSHOT_URL"
                        else -> "NEXUS_PUBLISH_RELEASE_URL"
                    }
                    setUrl(localProperties.getProperty(name))
                }
            }
            if (!isSnapshot(versionName)) {
                configure<SigningExtension> {
                    useGpgCmd()
                    sign(publications["maven"])
                }
            }
        }
    }
}

fun isSnapshot(versionName: String): Boolean = versionName.contains("SNAPSHOT")

inline fun <reified T> Project.getProperty(name: String): T = property(name) as T

inline val Project.localProperties: Properties
    get() = Properties().apply {
        load(project.rootProject.file("local.properties").inputStream())
    }
