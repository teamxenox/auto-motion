package com.theapache64.automotion.models

import com.google.gson.annotations.SerializedName


/**
 * To hold data returned from autosub
 */
data class AutoSubNode(
    @SerializedName("content")
    val content: String, // Britain to explore the boundaries of the pride territory
    @SerializedName("start")
    val start: Double, // 2.5600000000000005
    @SerializedName("end")
    val end: Double // 6.912000000000004
)