package com.theapache64.automotion.core

import com.winterbe.expekt.should
import org.junit.Test

import java.io.File

class FileUtilsTest {

    @Test
    fun getAudioDurationSuccess() {
        FileUtils.getDuration(File("lab/nervous.mp3")).should.equal(192.052245)
    }

    @Test
    fun testGetAudioDurationsSuccess() {
        FileUtils.getDuration(
            listOf(
                File("lab/nervous.mp3"),
                File("lab/suicidal.mp3")
            )
        ).should.equal(386.063674)
    }

    @Test
    fun getDimensionSuccess() {
        FileUtils.getDimension(File("lab/lion.mp4")).toString().should.equal("(1280, 720)")
    }

    @Test
    fun getDimensionMovieSuccess() {
        FileUtils.getDimension(File("lab/movie.mkv")).toString().should.equal("(1280, 544)")
    }

    @Test
    fun testHasSubTitleSuccess() {
        val movieFile = File("lab/movie.mkv")
        FileUtils.hasSubTitle(movieFile).should.`true`
    }


    @Test
    fun testHasSubTitleFailure() {
        val movieFile = File("lab/lion.mp4")
        FileUtils.hasSubTitle(movieFile).should.`false`
    }

    @Test
    fun testParseSubTitleSuccess() {
        val movieFile = File("lab/movie.mkv")
        val subTitleFile = FileUtils.parseSubTitle(movieFile)
        subTitleFile!!.name.should.equal("movie.srt")
    }
}