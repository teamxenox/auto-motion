package com.theapache64.automotion.models

/**
 * To hold each timelapse position and it's details
 */
data class Timelapse(
    val sourceStart: Double,
    val sourceEnd: Double,
    val srcDuration: Double,
    val targetDuration: Double,
    val targetStart: Double,
    val targetEnd: Double
)