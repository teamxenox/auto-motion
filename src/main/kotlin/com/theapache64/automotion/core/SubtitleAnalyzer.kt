package com.theapache64.automotion.core

import com.theapache64.automotion.models.AutoSubNode
import com.theapache64.automotion.models.SubtitleReport
import com.theapache64.automotion.models.Timelapse

/**
 * To analyze subtitles downloaded using autosub
 */
class SubtitleAnalyzer(
    private val subNodes: List<AutoSubNode>,
    private val minTimelapseSourceLength: Double,
    private val timelapseSpeed: Float,
    private val introDuration: Double
) {

    /**
     * To get timelapse timestamps and total timelapse duration
     */
    fun getReport(): SubtitleReport {

        val timelapses = mutableListOf<Timelapse>()
        val nonTimelapseDurations = mutableListOf<Double>() // to non-talking area but no timelapse
        var prevNode = AutoSubNode("", 0.0, 0.0) // first node

        var totalTimelapseDuration = 0.toDouble()

        for (subNode in subNodes) {

            val secDiff = subNode.start - prevNode.end

            if (secDiff >= minTimelapseSourceLength) {

                val targetDuration = secDiff * timelapseSpeed
                totalTimelapseDuration += targetDuration
                val sourceStart = prevNode.end
                val targetStart = getTargetStart(timelapses, nonTimelapseDurations, sourceStart)
                val targetEnd = targetStart + targetDuration

                val timelapse = Timelapse(
                    sourceStart,
                    sourceEnd = subNode.start,
                    srcDuration = secDiff,
                    targetDuration = targetDuration,
                    targetStart = targetStart,
                    targetEnd = targetEnd
                )

                timelapses.add(timelapse)
            } else {
                nonTimelapseDurations.add(secDiff)
            }

            prevNode = subNode
        }

        return SubtitleReport(totalTimelapseDuration, timelapses, nonTimelapseDurations)
    }

    private fun getTargetStart(
        timelapses: List<Timelapse>,
        nonTimelapseDurations: List<Double>,
        sourceStart: Double
    ): Double {
        return if (timelapses.isEmpty()) {
            introDuration + sourceStart
        } else {
            val talkSum = getSumOfTalkingBeforeSourceStart(subNodes, sourceStart) // total talking
            val timelapseSum = timelapses.sumByDouble { it.targetDuration } // total timelapse
            val nonTimelapseSum = nonTimelapseDurations.sumByDouble { it } // total non talking
            return introDuration + timelapseSum + talkSum + nonTimelapseSum
        }
    }

    private fun getSumOfTalkingBeforeSourceStart(subNodes: List<AutoSubNode>, sourceStart: Double): Double {
        return subNodes.filter { it.start < sourceStart }.sumByDouble { it.end - it.start }
    }

}