// settings.gradle (Groovy)
pluginManagement {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        // إذا تستخدم مكتبات من jitpack:
        maven { url 'https://jitpack.io' }
    }
}

rootProject.name = "Mizan"
include ':app'
