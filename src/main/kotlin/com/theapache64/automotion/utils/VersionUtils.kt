package com.theapache64.automotion.utils

import java.io.File
import java.lang.IllegalArgumentException

object VersionUtils {
    private val VERSION_REGEX = "^version '(.+)'".toRegex(RegexOption.MULTILINE)
    fun getVersion(): String {
        val buildGradle = File("build.gradle").readText()
        println(buildGradle)
        val matcher = VERSION_REGEX.find(buildGradle)
        if (matcher != null) {
            return matcher.groups[1]!!.value
        } else {
            throw  IllegalArgumentException("Failed to get version details")
        }

        return "x"
    }
}