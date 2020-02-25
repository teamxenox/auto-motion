package com.theapache64.automotion.core

import com.winterbe.expekt.should
import org.junit.Test
import java.io.File

class BgmCollectorTest {

    @Test
    fun testBgmCollector() {

        val totalBgmDuration = 8 * 60.0

        val bgmFiles = BgmAgent(
            listOf(
                File("lab/lost_in_time.mp3")
            ),
            totalBgmDuration,
            true
        ).getBgmFiles()

        val totalDuration = FileUtils.getDuration(bgmFiles)
        totalDuration.should.above(totalBgmDuration)
    }
}