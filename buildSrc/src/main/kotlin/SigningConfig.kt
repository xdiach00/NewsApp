import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import org.gradle.api.Project

@Serializable
data class SigningConfig(
    val id: String,
    val keyAlias: String,
    val keyPassword: String,
    val storeFile: String,
    val storePassword: String,
)

fun Project.loadSigningConfigurations(): List<SigningConfig> {
    val env = System.getenv("SIGNING_CONFIGURATION_FILE")
    val content = rootProject.file(env ?: "config/signing_config.json").readText()
    return Json.decodeFromString(content)
}
