// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.detekt) apply true
}

tasks.register("detektAll", io.gitlab.arturbosch.detekt.Detekt::class) {
    val autoFix = true //project.hasProperty("detektAutoFix")

    description = "Custom DETEKT build for all modules from MeguMethod"
    parallel = true
    ignoreFailures = false
    autoCorrect = autoFix
    buildUponDefaultConfig = true
    setSource(file(projectDir))
    config.setFrom(files("$rootDir/config/detekt/detekt.yml"))
    baseline.set(file("$rootDir/config/detekt/baseline.xml"))
    include("**/*.kt")
    exclude("**/resources/**", "**/build/**")
    reports {
        html.required.set(true)
        xml.required.set(true)
        txt.required.set(true)
    }
}

tasks.register("detektBaselineAll", io.gitlab.arturbosch.detekt.DetektCreateBaselineTask::class) {
    description = "Custom DETEKT baseline for all modules"
    setSource(file(projectDir))
    config.setFrom(files("$rootDir/config/detekt/detekt.yml"))
    baseline.set(file("$rootDir/config/detekt/baseline.xml"))
    include("**/*.kt")
    exclude("**/resources/**", "**/build/**")
}

dependencies {
    detektPlugins(libs.detekt.formatting)
    detektPlugins(libs.detekt.compose)
}
