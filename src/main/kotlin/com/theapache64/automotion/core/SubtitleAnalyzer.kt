package com.theapache64.automotion.core

import com.theapache64.automotion.models.AutoSubNode
import com.theapache64.automotion.models.SubtitleReport
import com.theapache64.automotion.models.Timelapse

/**
 * To analyze subtitles downloaded using autosub
 */
class SubtitleAnalyzer(
    private val minTimelapseSourceLength: Double,
    private val timelapseSpeed: Float,
    private val introDuration: Double
) {

    /**
     * To get timelapse timestamps and total timelapse duration
     */
    fun getReport(subNodes: List<AutoSubNode>): SubtitleReport {

        val timelapses = mutableListOf<Timelapse>()
        var prevNode = AutoSubNode("", 0.0, 0.0) // first node

        var totalTimelapseDuration = 0.toDouble()

        for (subNode in subNodes) {

            val secDiff = subNode.start - prevNode.end

            if (secDiff >= minTimelapseSourceLength) {
                val targetDuration = secDiff * timelapseSpeed
                totalTimelapseDuration += targetDuration
                val sourceStart = prevNode.end
                val targetStart = getTargetStart(subNodes, timelapses, sourceStart)
                val targetEnd = targetStart + targetDuration

                timelapses.add(
                    Timelapse(
                        sourceStart,
                        sourceEnd = subNode.start,
                        srcDuration = secDiff,
                        targetDuration = targetDuration,
                        targetStart = targetStart,
                        targetEnd = targetEnd
                    )
                )
            }

            prevNode = subNode
        }

        return SubtitleReport(totalTimelapseDuration, timelapses)
    }

    private fun getTargetStart(
        subNodes: List<AutoSubNode>,
        timelapses: MutableList<Timelapse>,
        sourceStart: Double
    ): Double {
        return if (timelapses.isEmpty()) {
            introDuration + sourceStart
        } else {
            val talkSum = getSumOfTalkingBeforeSourceStart(subNodes, sourceStart)
            return introDuration + timelapses.sumByDouble { it.targetDuration } + talkSum
        }
    }

    private fun getSumOfTalkingBeforeSourceStart(subNodes: List<AutoSubNode>, sourceStart: Double): Double {
        return subNodes.filter { it.start < sourceStart }.sumByDouble { it.end - it.start }
    }

}