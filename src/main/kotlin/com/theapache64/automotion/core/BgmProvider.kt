package com.theapache64.automotion.core

import com.theapache64.automotion.models.BgmNode
import java.io.File

/**
 * To provide BGMs
 */
class BgmProvider(
    bgmFile: File
) {

    private var totalDuration = FileUtils.getDuration(bgmFile)
    private var cursorAt = 0.0

    /**
     * To get bgm interval for the specified duration
     */
    fun getBgm(duration: Double): BgmNode {
        val start = cursorAt
        val end = start + duration
        require(end < totalDuration) { "DurationOutOfBound : available='$totalDuration', requested : '$end'" }
        val interval = Pair(start, end)
        cursorAt = end
        return BgmNode(interval)
    }
}