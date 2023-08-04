pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            name = "GitHubPackages"
            url = uri("https://maven.pkg.github.com/simonchius/Centralized-Version-Catalog")
            credentials {
                credentials {
                    username = System.getenv("USER_GITHUB_ID") ?: System.getProperty("USER_GITHUB_ID")
                    password = System.getenv("USER_GITHUB_ACCESS_TOKEN") ?: System.getProperty("USER_GITHUB_ACCESS_TOKEN")
                }
            }
        }
    }
    versionCatalogs{
        create("libs"){
            from("com.simonchius.android:version.catalog:1.1.0")
        }
    }

}

rootProject.name = "Centralized Version Catalog Consumer"
include(":app")
 