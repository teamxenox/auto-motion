package com.theapache64.automotion.core

import com.winterbe.expekt.should
import org.junit.Test
import java.io.File


class SubtitleAnalyzerTest {

    @Test
    fun testCalculation() {
        val jsonFile = File("lab/test.json")
        val subtitles = AutoSubUtils.parseJson(jsonFile, 180.0)

        val report = SubtitleAnalyzer(
            CommandParser.DEFAULT_MIN_TIMELAPSE_SRC_LENGTH.toDouble(),
            CommandParser.DEFAULT_TIMELAPSE_SPEED
        ).getReport(subtitles)

        val timelapses = report.timelapses
        timelapses.size.should.equal(4)
        timelapses.first().srcDuration.should.equal(20.toDouble())
        timelapses.first().targetDuration.should.equal(5.toDouble())
    }

    @Test
    fun testCalculationLion() {
        val jsonFile = File("lab/lion.json")
        val videoFile = File("lab/lion.mp4")
        val subtitles = AutoSubUtils.parseJson(jsonFile, FileUtils.getDuration(videoFile))
        val report = SubtitleAnalyzer(
            CommandParser.DEFAULT_MIN_TIMELAPSE_SRC_LENGTH.toDouble(),
            CommandParser.DEFAULT_TIMELAPSE_SPEED
        ).getReport(subtitles)
        val timelapses = report.timelapses
        timelapses.size.should.equal(2)
        report.totalTimelapseDuration.should.above(3.0)
    }

    @Test
    fun testCalculationBig() {
        val jsonFile = File("lab/comma.json")
        val duration = FileUtils.getDuration(File("lab/comma.mp4"))
        val subtitles = AutoSubUtils.parseJson(jsonFile, duration)
        val report = SubtitleAnalyzer(
            CommandParser.DEFAULT_MIN_TIMELAPSE_SRC_LENGTH.toDouble(),
            CommandParser.DEFAULT_TIMELAPSE_SPEED
        ).getReport(subtitles)

        val timelapses = report.timelapses
        timelapses.size.should.above(0)
        report.totalTimelapseDuration.should.above(100.toDouble())
    }

}