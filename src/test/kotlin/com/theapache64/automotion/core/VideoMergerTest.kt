package com.theapache64.automotion.core

import com.winterbe.expekt.should
import org.junit.Test
import java.io.File

class VideoMergerTest {

    @Test
    fun testMergeVideoSuccess() {

        val lionVideo = File("lab/lion_big.mp4")
        val sharkVideo = File("lab/shark.mp4")
        val inputVideoDuration = FileUtils.getDuration(lionVideo) + FileUtils.getDuration(sharkVideo)

        val inputVideos = listOf(
            lionVideo,
            sharkVideo
        )

        val outputVideo = VideoMerger.mergeVideo(inputVideos)
        val outputVideoLength = FileUtils.getDuration(outputVideo)

        kotlin.math.floor(inputVideoDuration).should.equal(kotlin.math.floor(outputVideoLength))
    }

    @Test
    fun testMergeVideoDiffFormatFailure() {

        val lionVideo = File("lab/lion.mp4")
        val sharkVideo = File("lab/shark.mp4")
        val inputVideoDuration = FileUtils.getDuration(lionVideo) + FileUtils.getDuration(sharkVideo)

        val inputVideos = listOf(
            lionVideo,
            sharkVideo
        )

        val outputVideo = VideoMerger.mergeVideo(inputVideos)
        val outputVideoLength = FileUtils.getDuration(outputVideo)

        kotlin.math.floor(inputVideoDuration).should.not.equal(kotlin.math.floor(outputVideoLength))
    }
}