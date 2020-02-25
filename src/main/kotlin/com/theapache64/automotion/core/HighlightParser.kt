package com.theapache64.automotion.core

import java.lang.IllegalArgumentException
import java.util.concurrent.TimeUnit

object HighlightParser {

    private val HIGHLIGHT_INPUT_REGEX = "(?<HH>\\d+):(?<mm>\\d+):(?<ss>\\d+)-(?<seconds>\\d+)".toRegex()

    fun parse(string: String): Pair<Double, Double> {

        val matchResult = HIGHLIGHT_INPUT_REGEX.matchEntire(string)
        val groups = matchResult?.groups

        if (groups != null) {
            val HH = groups["HH"]!!.value.toLong()
            val mm = groups["mm"]!!.value.toLong()
            val ss = groups["ss"]!!.value.toLong()
            val seconds = groups["seconds"]!!.value.toLong()

            // converting to seconds
            val hhSec = TimeUnit.HOURS.toSeconds(HH)
            val mmSec = TimeUnit.MINUTES.toSeconds(mm)

            val totalSec = hhSec + mmSec + ss
            return Pair(totalSec.toDouble(), (totalSec + seconds).toDouble())
        } else {
            throw IllegalArgumentException("Invalid time format $string")
        }

    }
}