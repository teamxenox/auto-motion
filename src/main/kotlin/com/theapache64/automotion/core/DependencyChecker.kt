package com.theapache64.automotion.core

import com.theapache64.automotion.exceptions.DependencyException
import com.theapache64.automotion.utils.SimpleCommandExecutor

object DependencyChecker {

    private const val MIN_FFMPEG_VERSION = 422
    private const val FFMPEG_VERSION_START = "ffmpeg version"
    private val VERSION_REPLACE_REGEX = "[a-zA-Z\\s.]+".toRegex()

    fun isFFmpegOkay(): Boolean {

        val versionDetails = SimpleCommandExecutor.executeCommand("ffmpeg -version")

        if (versionDetails.startsWith(FFMPEG_VERSION_START)) {
            // checking version
            val curV = versionDetails.split("-")[0]
            val currentVersion = curV.replace(VERSION_REPLACE_REGEX, "").toLong()
            println(currentVersion)
            if (currentVersion >= MIN_FFMPEG_VERSION) {
                return true
            } else {
                throw DependencyException("ffmpeg version should be >= 4.2.2 but $curV")
            }
        } else {
            throw DependencyException("ffmpeg not installed")
        }
    }
}