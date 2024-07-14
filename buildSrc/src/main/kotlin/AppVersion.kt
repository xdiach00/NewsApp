import kotlinx.serialization.Serializable
import org.gradle.api.Project
import kotlinx.serialization.json.*

@Serializable
data class AppVersion(
    val major: Int,
    val minor: Int,
    val patch: Int,
    val releaseCandidate: Int,
) {
    fun versionCode() = ((major * OFFSET + minor) * OFFSET + patch) * OFFSET + releaseCandidate

    fun versionName() = "$major.$minor.$patch.$releaseCandidate"

    fun shortVersionName() = "$major.$minor.$patch"

    companion object {
        private const val OFFSET = 100
    }
}

fun Project.loadAppVersion(): AppVersion {
    val content = rootProject.file("config/app_version.json").readText()
    return Json.decodeFromString(content)
}