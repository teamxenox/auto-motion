package com.theapache64.automotion.core

import com.theapache64.automotion.models.AutoSubNode
import com.theapache64.automotion.models.SubtitleReport
import com.theapache64.automotion.models.Timelapse

/**
 * To analyze subtitles downloaded using autosub
 */
class SubtitleAnalyzer(
    private val minTimelapseSourceLength: Double,
    private val timelapseSpeed: Float
) {

    /**
     * To get timelapse timestamps and total timelapse duration
     */
    fun getReport(subNodes: List<AutoSubNode>): SubtitleReport {

        val list = mutableListOf<Timelapse>()
        var prevNode: AutoSubNode? = null

        var totalTimelapseDuration = 0.toDouble()

        for (subNode in subNodes) {
            if (prevNode == null) {
                // first
                prevNode = subNode
                continue
            }

            val secDiff = subNode.start - prevNode.end
            if (secDiff >= minTimelapseSourceLength) {
                val targetDuration = secDiff * timelapseSpeed
                totalTimelapseDuration += targetDuration
                list.add(Timelapse(prevNode.end, subNode.start, secDiff, targetDuration))
            }

            prevNode = subNode
        }

        return SubtitleReport(totalTimelapseDuration, list)
    }

}