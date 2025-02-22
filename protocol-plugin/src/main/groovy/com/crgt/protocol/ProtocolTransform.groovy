package com.crgt.protocol

import org.gradle.api.Plugin
import org.gradle.api.Project

class ProtocolTransform implements Plugin<Project> {

    @Override
    void apply(Project project) {

        String versionName = ""

        project.buildscript.configurations.classpath.resolvedConfiguration.firstLevelModuleDependencies.forEach {
            println "helper_parse_version = " + it.name
            if (it.name.startsWith("com.crgt.android:protocol-plugin")) {
                versionName = it.moduleVersion
                return
            }
        }

        project.subprojects { Project p ->
            p.afterEvaluate {
                if (it.plugins.hasPlugin("com.android.application")) {
                    it.plugins.apply('com.crgt.protocolimpl')
                }

                if (it.plugins.hasPlugin("com.android.application") || it.plugins.hasPlugin("com.android.library")) {
                    it.dependencies.add('implementation', "com.crgt.android:base-protocol-api:1.0.0")
                    it.dependencies.add('annotationProcessor', "com.crgt.android:base-protocol-compiler:1.0.0")

                    it.android.defaultConfig.javaCompileOptions.annotationProcessorOptions.argument('moduleName', it.name)
                }
            }
        }
    }
}