package com.theapache64.automotion.core

import com.winterbe.expekt.should
import org.junit.Assert.*
import org.junit.Test
import java.io.File
import java.lang.IllegalArgumentException

class BgmProviderTest {

    @Test
    fun testBgmProviderSuccess() {

        val lostInTime = File("lab/lost_in_time.mp3")
        val suicidal = File("lab/suicidal.mp3")
        val nervous = File("lab/nervous.mp3")

        val bgmProvider = BgmProvider(
            AudioMerger(
                listOf(
                    lostInTime,
                    suicidal,
                    nervous
                )
            ).merge()
        )

        val bgm1 = bgmProvider.getBgm(10.0)
        bgm1.interval.first.should.equal(0.0)
        bgm1.interval.second.should.equal(10.0)


        val bgm2 = bgmProvider.getBgm(25.0)
        bgm2.interval.first.should.equal(11.0)
        bgm2.interval.second.should.equal(36.0)


        val bgm3 = bgmProvider.getBgm(60.0)
        bgm3.interval.first.should.equal(37.0)
        bgm3.interval.second.should.equal(97.0)

        val bgm4 = bgmProvider.getBgm(10.5)
        bgm4.interval.first.should.equal(98.0)
        bgm4.interval.second.should.equal(108.5)

        try {
            bgmProvider.getBgm(100000000000.0)
            assert(false)
        } catch (e: IllegalArgumentException) {
            assert(true)
        }
    }
}