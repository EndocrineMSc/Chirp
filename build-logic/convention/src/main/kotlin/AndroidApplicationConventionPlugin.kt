import com.android.build.api.dsl.ApplicationExtension
import com.endocrine.chirp.convention.configureKotlinAndroid
import com.endocrine.chirp.convention.libs
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.kotlin.dsl.configure

/**
 * This plugin allows to set repeated gradle config within a single place and reference it
 * throughout a multimodular project.
 */
class AndroidApplicationConventionPlugin : Plugin<Project> {

    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("com.android.application")
            }

            extensions.configure<ApplicationExtension> {
                namespace = "com.endocrine.chirp"


                // The defaultConfig is only 'called' in the composeApp build.gradle.kts, hence
                // it can be statically set here
                defaultConfig {
                    applicationId = libs.findVersion("projectApplicationId").get().toString()
                    targetSdk = libs.findVersion("projectTargetSdkVersion").get().toString().toInt()
                    versionCode = libs.findVersion("projectVersionCode").get().toString().toInt()
                    versionName = libs.findVersion("projectVersionName").get().toString()
                }
                packaging {
                    resources {
                        excludes += "/META-INF/{AL2.0,LGPL2.1}"
                    }
                }
                buildTypes {
                    getByName("release") {
                        isMinifyEnabled = false
                    }
                }

                configureKotlinAndroid(this)
            }
        }
    }
}