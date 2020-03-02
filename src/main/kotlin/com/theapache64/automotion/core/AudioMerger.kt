package com.theapache64.automotion.core

import com.theapache64.automotion.utils.ComplexCommandExecutor
import java.io.File
import java.lang.StringBuilder

class AudioMerger(private val bgmFiles: List<File>) {

    init {
        require(bgmFiles.size > 1) { "File list can't be empty and the file count must be >=2" }
    }

    fun merge(): File {

        val firstFile = bgmFiles.first()
        val outputFile = File("${firstFile.parent}/merged.${firstFile.extension}")

        if (outputFile.exists()) {
            outputFile.delete()
        }

        val command = buildCommand(outputFile)
        ComplexCommandExecutor.executeCommand(
            command,
            isLivePrint = true,
            isSuppressError = true,
            isReturnAll = true,
            isSameLinePrint = { true },
            isNoPrint = { line -> !line.contains("bitrate= ") },
            prefix = "\uD83C\uDF00 merging : "
        )

        return outputFile
    }

    private fun buildCommand(outputFile: File): String {
        val sb = StringBuilder("ffpb ")

        // Adding input files
        bgmFiles.forEach { bgmFile ->
            sb.append(" -i ${bgmFile.absolutePath}")
        }

        sb.append(" -filter_complex \"")
        for (i in bgmFiles.indices) {
            sb.append("[$i:0]")
        }

        sb.append("concat=n=${bgmFiles.size}:v=0:a=1[out]\" -map '[out]' $outputFile")

        return sb.toString()
    }

}