package plugins

import org.gradle.api.Plugin
import org.gradle.api.Project

/*
*  @see https://medium.com/bumble-tech/how-to-use-composite-builds-as-a-replacement-of-buildsrc-in-gradle-64ff99344b58
*  @see https://github.com/badoo/Reaktive/blob/59a612fc6139a28335b92536be63d27e4aeb83f8/buildSrc/build.gradle.kts
* */

class ClassLoaderPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        // no-op
    }

}

object Deps {
    const val SomeDep = ""
}