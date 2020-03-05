package com.theapache64.automotion.core

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.winterbe.expekt.should
import org.junit.Test
import java.io.File
import kotlin.math.roundToInt


class SubtitleAnalyzerTest {

    @Test
    fun testCalculation() {
        val jsonFile = File("lab/test.json")
        val subtitles = AutoSubUtils.parseJson(jsonFile, 180.0)

        val report = SubtitleAnalyzer(
            subtitles,
            CommandParser.DEFAULT_MIN_TIMELAPSE_SRC_LENGTH,
            CommandParser.DEFAULT_TIMELAPSE_SPEED,
            CommandParser.DEFAULT_INTRO_DURATION
        ).getReport()

        val timelapses = report.timelapses
        timelapses.size.should.equal(4)
        timelapses.first().srcDuration.should.equal(15.0)
        timelapses.first().targetDuration.should.equal(3.75)
    }


    @Test
    fun testTargetStartEndSuccess() {

        val jsonFile = File("lab/lion_2.json")
        val videoFile = File("lab/lion.mp4")
        val videoDuration = FileUtils.getDuration(videoFile)
        val subtitles = AutoSubUtils.parseJson(jsonFile, videoDuration)

        val timelapseSpeed = 0.25f

        val report = SubtitleAnalyzer(
            subtitles,
            minTimelapseSourceLength = 2.0 / timelapseSpeed,
            timelapseSpeed = timelapseSpeed,
            introDuration = 3.0
        ).getReport()

        val timelapses = report.timelapses


        println(GsonBuilder().setPrettyPrinting().create().toJson(timelapses))

        // first timelapse
        timelapses[0].targetStart.roundToInt().should.equal(18)
        timelapses[0].targetEnd.roundToInt().should.equal(21)

        timelapses[1].targetStart.roundToInt().should.equal(27)
        timelapses[1].targetEnd.roundToInt().should.equal(33)
    }

    @Test
    fun test2TargetStartEndSuccess() {
        val jsonFile = File("lab/test_timelapse_target_start_end.json")
        val subtitles = AutoSubUtils.parseJson(jsonFile, 540.0)


        val timelapseSpeed = 0.25f

        val report = SubtitleAnalyzer(
            subtitles,
            minTimelapseSourceLength = 2.0 / timelapseSpeed,
            timelapseSpeed = timelapseSpeed,
            introDuration = 3.0
        ).getReport()

        val timelapses = report.timelapses

        timelapses.size.should.equal(4)

        // first timelapse
        timelapses[0].targetStart.roundToInt().should.equal(63)
        timelapses[0].targetEnd.roundToInt().should.equal(78)

        timelapses[1].targetStart.roundToInt().should.equal(138)
        timelapses[1].targetEnd.roundToInt().should.equal(153)

        timelapses[2].targetStart.roundToInt().should.equal(213)
        timelapses[2].targetEnd.roundToInt().should.equal(228)

        timelapses[3].targetStart.roundToInt().should.equal(288)
        timelapses[3].targetEnd.roundToInt().should.equal(303)
    }

    @Test
    fun testCalculationLion() {

        val jsonFile = File("lab/lion_2.json")
        val videoFile = File("lab/lion.mp4")

        val subtitles = AutoSubUtils.parseJson(jsonFile, FileUtils.getDuration(videoFile))

        val report = SubtitleAnalyzer(
            subtitles,
            CommandParser.DEFAULT_MIN_TIMELAPSE_SRC_LENGTH,
            CommandParser.DEFAULT_TIMELAPSE_SPEED,
            CommandParser.DEFAULT_INTRO_DURATION
        ).getReport()

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
            subtitles,
            CommandParser.DEFAULT_MIN_TIMELAPSE_SRC_LENGTH,
            CommandParser.DEFAULT_TIMELAPSE_SPEED,
            CommandParser.DEFAULT_INTRO_DURATION
        ).getReport()

        val timelapses = report.timelapses
        timelapses.size.should.above(0)
        report.totalTimelapseDuration.should.above(100.0)
    }

}