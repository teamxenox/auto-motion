package com.theapache64.automotion.core

import com.theapache64.automotion.exceptions.DependencyException
import com.theapache64.automotion.utils.SimpleCommandExecutor

object DependencyChecker {

    private const val FFMPEG_VERSION_START = "ffmpeg version"
    private fun isFFmpegOkay(): Boolean {
        val versionDetails = SimpleCommandExecutor.executeCommand("ffmpeg -version")
        if (versionDetails.startsWith(FFMPEG_VERSION_START)) {
            // checking version
            val x = versionDetails.split("-")[0].replace("[]".toRegex())
        } else {
            throw DependencyException("ffmpeg not installed")
        }
    }
}