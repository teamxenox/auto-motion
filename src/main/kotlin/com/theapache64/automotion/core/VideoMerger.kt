package com.theapache64.automotion.core

import com.theapache64.automotion.utils.ComplexCommandExecutor
import java.io.File
import java.io.IOException

/**
 * To merge video files
 */
object VideoMerger {

    private const val NO_FILE_FOUND_ERROR = "No such file or directory"

    /**
     * To merge video files.
     */
    fun mergeVideo(
        videoList: List<File>
    ): File {

        // Create merge_list.txt
        val mergeSb = StringBuilder()

        for (videoFile in videoList) {
            mergeSb.append("file '${videoFile.absolutePath}'\n")
        }

        val videoParent = videoList.first().parent
        val mergeListFile = File("$videoParent/merge_list_${System.currentTimeMillis()}.txt")
        mergeListFile.writeText(mergeSb.toString())
        val mergeFile = File("$videoParent/merged_${System.currentTimeMillis()}.${videoList.first().extension}")

        val ffmpegCommand =
            "ffmpeg -y -f concat -safe 0 -i ${mergeListFile.absolutePath} -c copy '${mergeFile.absolutePath}'"
        val result = ComplexCommandExecutor.executeCommand(
            ffmpegCommand,
            true,
            { true },
            { false },
            true,
            isReturnAll = true
        ).joinToString(separator = "\n")

        if (result.contains(NO_FILE_FOUND_ERROR)) {
            val errorFile = parseImpossibleFile(result)
            throw IOException("File not found '${errorFile.absolutePath}'")
        }

        mergeListFile.delete()

        return mergeFile
    }

    private val IMP_FILE_ERROR_REGEX = "Impossible to open '(\\.+)'".toRegex()
    private fun parseImpossibleFile(result: String): File {
        val matcher = IMP_FILE_ERROR_REGEX.find(result)
        if (matcher != null) {
            val missingFileName = matcher.groups[1]!!.value
            println("Missing is " + missingFileName)
            return File(missingFileName)
        }
        throw IllegalArgumentException("Failed to get error file name")
    }
}