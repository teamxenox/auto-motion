package com.theapache64.automotion.utils

import java.text.SimpleDateFormat
import java.util.*
import kotlin.math.roundToLong

/**
 * To format times in seconds to readable format ( XX:XX:XX )
 */
object DateTimeUtils {

    private val DATE_FORMAT = SimpleDateFormat("MMM dd yyyy")

    fun getDateFormatted(date: Date): String {
        return DATE_FORMAT.format(date)
    }

    fun getTimeFormatted(totalSecs: Int): String {
        return getTimeFormatted(totalSecs.toDouble())
    }

    /**
     * To get seconds in XX:XX:XX format
     */
    fun getTimeFormatted(_totalSecs: Double): String {
        val totalSecs = _totalSecs.roundToLong()
        val hours = (totalSecs / 3600)
        val minutes = ((totalSecs % 3600) / 60)
        val seconds = (totalSecs % 60)
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
}