package com.theapache64.automotion.core


import com.theapache64.automotion.core.srtparser.SrtParser
import com.winterbe.expekt.should
import org.junit.Test
import java.io.File

class AutoSubUtilsTest {

    @Test
    fun testGetSubSuccess() {
        val subTitles =
            AutoSubUtils.getSubFor(File("lab/lion.mp4"), "en")

        subTitles.size.should.above(0)
    }

    @Test
    fun testGetSubFail() {
        try {
            AutoSubUtils.getSubFor(File("lab/INVALID_FILE.mp4"), "en")
            assert(false)
        } catch (e: IllegalArgumentException) {
            assert(true)
        }
    }

    @Test
    fun testSrtParsingSuccess() {
        val srtFile = File("lab/lion.srt")
        println(srtFile.absolutePath)
        val parser = SrtParser().parse(srtFile)
        parser.subtitles.size.should.equal(3)
    }
}