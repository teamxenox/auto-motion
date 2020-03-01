package com.theapache64.automotion.core.srtparser

data class Subtitle(val index: Long, val begin: Timestamp, val end: Timestamp, val text: String) {
    fun serialize(): String = "$index\n" + begin.serialize() + " --> " + end.serialize() + "\n$text"
}