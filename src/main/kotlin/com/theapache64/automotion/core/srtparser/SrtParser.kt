package com.theapache64.automotion.core.srtparser

import java.io.File
import java.nio.charset.Charset

class IllegalTimestampFormatException(override val message: String) : RuntimeException()

class IllegalSrtFormatException(override val message: String) : RuntimeException()

class SrtParser {
    var subtitles = emptyList<Subtitle>()
        private set

    @Throws(IllegalSrtFormatException::class)
    fun parse(file: File, charset: Charset = Charsets.UTF_8): SrtParser {

        val reader = file.bufferedReader(charset)
        val subtitleList = mutableListOf<Subtitle>()

        reader.useLines {
            var index: Long? = null
            var begin: Timestamp? = null
            var end: Timestamp? = null
            var text: String? = null

            for (line in it.map { line -> realTrim(line) }) {

                if (line.isNotEmpty()) {
                    when {
                        (index == null) -> {
                            if (line.startsWith("\uFEFF")) {
                                println("Line starting with space '$line'")
                                println("trimming...")
                                val newLine = line.trim()
                                println("New line is '$newLine'")
                            }
                            index = line.toLongOrNull() ?: throw IllegalSrtFormatException(
                                "Invalid index: '${line.trim()}'"
                            )
                        }
                        (begin == null) -> {
                            val timestamps = line.split(" --> ")

                            if (timestamps.size != 2) {
                                throw IllegalSrtFormatException(
                                    "Invalid time span: '$line'"
                                )
                            }
                            try {
                                begin = parseTimestamp(
                                    timestamps[0].trim()
                                )
                            } catch (e: IllegalTimestampFormatException) {
                                throw IllegalSrtFormatException(
                                    e.message
                                )
                            }
                            try {
                                end = parseTimestamp(
                                    timestamps[1].trim()
                                )
                            } catch (e: IllegalTimestampFormatException) {
                                throw IllegalSrtFormatException(
                                    e.message
                                )
                            }
                        }
                        (text == null) -> text = line
                        else -> text += "\n$line"
                    }
                } else if (text != null) {
                    subtitleList.add(
                        Subtitle(
                            index!!,
                            begin!!,
                            end!!,
                            text
                        )
                    )
                    index = null
                    begin = null
                    end = null
                    text = null
                }
            }
        }

        subtitleList.sortBy { it -> it.index } // ensure subtitles are sorted correctly according to index
        subtitles = subtitleList.toList()
        return this
    }

    private fun realTrim(line: String): String {
        return line.trim().replace("\uFEFF", "")
    }
}

fun parseTimestamp(timestamp: String): Timestamp {
    try {
        val parts = timestamp.split(":", ",")
        return Timestamp(parts[0].toInt(), parts[1].toInt(), parts[2].toInt(), parts[3].toInt())
    } catch (e: Exception) {
        throw IllegalTimestampFormatException("Invalid timestamp: '$timestamp'")
    }
}