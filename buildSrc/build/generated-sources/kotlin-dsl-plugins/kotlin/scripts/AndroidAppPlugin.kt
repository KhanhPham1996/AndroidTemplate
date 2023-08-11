package scripts


/**
 * Precompiled [android-app.gradle.kts][scripts.Android_app_gradle] script plugin.
 *
 * @see scripts.Android_app_gradle
 */
public
class AndroidAppPlugin : org.gradle.api.Plugin<org.gradle.api.Project> {
    override fun apply(target: org.gradle.api.Project) {
        try {
            Class
                .forName("scripts.Android_app_gradle")
                .getDeclaredConstructor(org.gradle.api.Project::class.java, org.gradle.api.Project::class.java)
                .newInstance(target, target)
        } catch (e: java.lang.reflect.InvocationTargetException) {
            throw e.targetException
        }
    }
}
