package com.theapache64.automotion.core

import com.winterbe.expekt.should
import org.junit.Test
import java.io.File
import kotlin.math.ceil

class AudioMergerTest {

    @Test
    fun testAudioMergerSuccess() {
        val lostInTime = File("lab/lost_in_time.mp3")
        val suicidal = File("lab/suicidal.mp3")
        val nervous = File("lab/nervous.mp3")

        val inputAudios = listOf(lostInTime, suicidal, nervous)
        val mergedFile = if (inputAudios.size > 1) {
            AudioMerger(inputAudios).merge()
        } else {
            inputAudios.first()
        }

        val inputAudiosLength = FileUtils.getDuration(inputAudios)
        val targetAudioLength = FileUtils.getDuration(mergedFile)
        ceil(inputAudiosLength).should.equal(ceil(targetAudioLength))
    }
}