package com.theapache64.automotion.core

import com.theapache64.automotion.exceptions.DependencyException
import com.theapache64.automotion.utils.SimpleCommandExecutor

object DependencyChecker {

    private const val MIN_FFMPEG_VERSION = 422
    private const val FFMPEG_VERSION_START = "ffmpeg version"
    private const val FFPB_OUTPUT_START = "Hyper fast Audio and Video encoder"
    private val VERSION_REPLACE_REGEX = "[a-zA-Z\\s.]+".toRegex()
    private const val AUTOSUB_HELP = "usage: autosub"

    fun isFFmpegOkay(): Boolean {

        val versionDetails = SimpleCommandExecutor.executeCommand("ffmpeg -version")

        if (versionDetails.startsWith(FFMPEG_VERSION_START)) {
            // checking version
            val curV = versionDetails.split("-")[0]
            val currentVersion = curV.replace(VERSION_REPLACE_REGEX, "").toLong()
            if (currentVersion >= MIN_FFMPEG_VERSION) {
                return true
            } else {
                throw DependencyException("ffmpeg version should be >= 4.2.2 but $curV")
            }
        } else {
            throw DependencyException("ffmpeg not installed")
        }
    }

    fun isAutoSubOkay(): Boolean {

        val isAutoSubOkay = SimpleCommandExecutor.executeCommand("autosub -h").startsWith(AUTOSUB_HELP)

        if (isAutoSubOkay) {
            return true
        } else {
            throw DependencyException("AutoSub not installed")
        }
    }

    fun isFFPBOkay(): Boolean {
        val commandOutput = SimpleCommandExecutor.executeCommand("ffpb --help")
        val isOkay = commandOutput
            .contains(FFPB_OUTPUT_START)
        if (isOkay) {
            return true
        } else {
            throw DependencyException("ffpb not installed")
        }
    }
}