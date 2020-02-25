package com.theapache64.automotion.core

import com.theapache64.automotion.utils.SimpleCommandExecutor
import java.io.File

/**
 * Operations related to files
 */
object FileUtils {

    /**
     * To get duration of a media file in seconds
     */
    fun getDuration(file: File): Double {
        val command = "ffprobe -i \"${file.absolutePath}\" -show_entries format=duration -v quiet -of csv=\"p=0\""
        val output =
            SimpleCommandExecutor.executeCommand(command)
        return output.toDouble()
    }

    /**
     * To get duration of multiple media files in seconds
     */
    fun getDuration(files: List<File>): Double {
        var duration = 0.0
        files.forEach { file ->
            duration += getDuration(file)
        }
        return duration
    }

    /**
     * To get video dimensions
     * first = width
     * second = height
     */
    fun getDimension(videoFile: File): Pair<Int, Int> {
        val result =
            SimpleCommandExecutor.executeCommand("ffprobe -v error -show_entries stream=width,height -of csv=p=0:s=x \"${videoFile.absolutePath}\"")
        val widthHeight = result.split("x")
        return Pair(widthHeight[0].trim().toInt(), widthHeight[1].trim().toInt())
    }
}