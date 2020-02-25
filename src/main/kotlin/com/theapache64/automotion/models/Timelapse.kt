package com.theapache64.automotion.models

/**
 * To hold each timelapse position and it's details
 */
data class Timelapse(
    val start: Double,
    val end: Double,
    val srcDuration: Double,
    val targetDuration: Double
)