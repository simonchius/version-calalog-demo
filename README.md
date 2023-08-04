# How to consume in Project/Module
We need to set the version catalog entry point in settings.gradle.kts
```
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven {
            // Do not forget to change the repository url
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
        // Do not forget to change the value
        create("libs"){
            from("com.simonchius.android:version.catalog:1.0.7")
        }
    }

}

```

That's it, now we can use the centralized dependencies under the build.gradle.kts file
```
dependencies {

    implementation(libs.core.ktx)
    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.constraintlayout)
    implementation(libs.navigation.fragment.ktx)
    implementation(libs.navigation.ui.ktx)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.test.ext.junit)
    androidTestImplementation(libs.espresso.core)
}
```
These dependency versions are all fetched from our centralized version catalog.
Note : 

```
If you face an issue like "In version catalog libs, you can only call the 'from' method a single time"
* Try:
> Run with --info or --debug option to get more log output.
> Run with --scan to get full insights.

* Exception is:
org.gradle.api.InvalidUserDataException: Invalid catalog definition:
  - Problem: In version catalog libs, you can only call the 'from' method a single time.
    
    Reason: The method was called more than once.
    
    Possible solution: Remove further usages of the method call.
    
    Please refer to https://docs.gradle.org/8.1/userguide/version_catalog_problems.html#too_many_import_invocation for more details about this problem.
 at org.gradle.api.internal.catalog.problems.DefaultCatalogProblemBuilder.maybeThrowError(DefaultCatalogProblemBuilder.java:66)
 at org.gradle.api.internal.catalog.DefaultVersionCatalogBuilder.throwVersionCatalogProblem(DefaultVersionCatalogBuilder.java:403)
 at org.gradle.api.internal.catalog.DefaultVersionCatalogBuilder.from(DefaultVersionCatalogBuilder.java:250)

 at org.gradle.configuration.BuildTreePreparingProjectsPreparer.lambda$generateDependenciesAccessorsAndAssignPluginVersions$0(BuildTreePreparingProjectsPreparer.java:89 
```

You just have to delete your default libs.versions.toml file and resync the gradle. it will work.
