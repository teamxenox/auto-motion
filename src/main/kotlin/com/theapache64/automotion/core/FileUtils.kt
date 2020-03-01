package com.theapache64.automotion.core

import com.theapache64.automotion.utils.SimpleCommandExecutor
import java.io.File
import java.io.FileNotFoundException
import java.io.IOException

/**
 * Operations related to files
 */
object FileUtils {

    /**
     * To get duration of a media file in seconds
     */
    fun getDuration(file: File): Double {
        if (file.exists()) {
            val command =
                "ffprobe -i \"${file.absolutePath}\" -show_entries format=duration -v quiet -of csv=\"p=0\""
            val output =
                SimpleCommandExecutor.executeCommand(command)
            return output.toDouble()
        } else {
            throw FileNotFoundException(file.absolutePath)
        }
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
        val command = "ffprobe -v error -show_entries stream=width,height -of csv=p=0:s=x \"${videoFile.absolutePath}\""
        println(command)
        val result =
            SimpleCommandExecutor.executeCommand(command)
        val widthHeight = result.trim().split("\n")[0].split("x")
        return Pair(widthHeight[0].trim().toInt(), widthHeight[1].trim().toInt())
    }

    fun parseSubTitle(inputFile: File): File? {

        return if (hasSubTitle(inputFile)) {
            val subTitleFilePath = "${inputFile.parentFile.absolutePath}/${inputFile.nameWithoutExtension}.srt"
            val command =
                "ffmpeg -y -i \"${inputFile.absolutePath}\" -map 0:s:0 \"$subTitleFilePath\""
            SimpleCommandExecutor.executeCommand(command, false, true, true)
            val subTitleFile = File(subTitleFilePath)

            if (subTitleFile.exists()) {
                subTitleFile
            } else {
                null
            }

        } else {
            null
        }
    }

    fun hasSubTitle(inputFile: File): Boolean {
        return SimpleCommandExecutor.executeCommand("ffmpeg -i \"${inputFile.absolutePath}\" -c copy -map 0:s -f null - -v 0 -hide_banner && echo \$? || echo \$?\n") == "0"
    }
}