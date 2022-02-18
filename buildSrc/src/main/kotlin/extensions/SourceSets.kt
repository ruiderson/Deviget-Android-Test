package extensions

import com.android.build.api.dsl.AndroidSourceSet
import org.gradle.api.NamedDomainObjectContainer

internal fun NamedDomainObjectContainer<out AndroidSourceSet>.setupDefaultSources() = with(this) {

    getByName("main") {
        java.srcDir(Sources.MAIN)
    }

    getByName("test") {
        java.srcDir(Sources.UNIT_TEST)
    }

    getByName("androidTest") {
        java.srcDir(Sources.ANDROID_TEST)
    }
}
