package com.theapache64.automotion.models

import com.google.gson.annotations.SerializedName

class FFProbeOutput(
    @SerializedName("streams")
    val streams: List<VideoMeta>
)

data class VideoMeta(
    @SerializedName("duration")
    val duration: Double, // 14.973289
    @SerializedName("height")
    val height: Int, // 544
    @SerializedName("sample_aspect_ratio")
    val sampleAspectRatio: String, // 1299:1280
    @SerializedName("width")
    val width: Int // 1280
)

