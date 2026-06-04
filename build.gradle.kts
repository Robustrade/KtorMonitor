import java.time.Year

plugins {
    alias(libs.plugins.androidApplication) apply false
    alias(libs.plugins.androidLibrary) apply false
    alias(libs.plugins.composeMultiplatform) apply false
    alias(libs.plugins.composeCompiler) apply false
    alias(libs.plugins.kotlinMultiplatform) apply false
    alias(libs.plugins.sqldelight) apply false
    alias(libs.plugins.kotlinx.atomicfu) apply false
    alias(libs.plugins.maven.publish) apply false
    alias(libs.plugins.binary.compatibility.validator) apply false
    alias(libs.plugins.dokka)
}

allprojects {
    group = "robustrade"
    version = "1.13.0"
}

subprojects {
    plugins.withId("maven-publish") {
        extensions.configure<PublishingExtension>("publishing") {
            repositories {
                maven {
                    name = "GitHubPackages"
                    url = uri(
                        providers.gradleProperty("gpr.url")
                            .orElse(providers.environmentVariable("GITHUB_PACKAGES_URL"))
                            .getOrElse("https://maven.pkg.github.com/Robustrade/KtorMonitor")
                    )
                    credentials {
                        username = providers.gradleProperty("gpr.user")
                            .orElse(providers.environmentVariable("GITHUB_ACTOR"))
                            .orNull
                        password = providers.gradleProperty("gpr.key")
                            .orElse(providers.environmentVariable("GITHUB_TOKEN"))
                            .orNull
                    }
                }
            }
        }
    }
}

dependencies {
    dokka(project(":ktor:library-ktor"))
}

dokka {
    val docsDir = File(rootDir, "docs/docs")

    moduleName = "Ktor Monitor"
    moduleVersion = project.version.toString()

    dokkaPublications.html {
        outputDirectory.set(File(docsDir, "api"))
    }

    pluginsConfiguration.html {
        customAssets.from(File(docsDir, "images/logo-icon.svg"))
        footerMessage.set("© ${Year.now().value} Cosmin Mihu")
    }
}
