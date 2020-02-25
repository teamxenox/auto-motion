package com.theapache64.automotion.core

import com.theapache64.automotion.utils.InputUtils
import com.theapache64.automotion.utils.DateTimeUtils
import java.io.File

/**
 * To collect BGM from user via console
 */
class BgmAgent(
    passedBgms: List<File>,
    private val totalBgmDuration: Double,
    private val isFromTest: Boolean
) {

    private var passedBgmDuration = 0.0
    private val finalBgms = mutableListOf<File>()

    init {
        finalBgms.addAll(passedBgms)
        updateBgmStatus()
    }

    /**
     * To update BGM status and collect more if needed.
     */
    private fun updateBgmStatus() {

        passedBgmDuration = FileUtils.getDuration(finalBgms)
        val remBgDur = totalBgmDuration - passedBgmDuration

        println("↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️")
        println("↪ Total BGM duration : ${DateTimeUtils.getTimeFormatted(totalBgmDuration)}")
        println("↪ Passed BGM duration : ${DateTimeUtils.getTimeFormatted(passedBgmDuration)}")

        if (remBgDur > 0) {

            println("❗ Remaining BGM needed : ${DateTimeUtils.getTimeFormatted(remBgDur)}")

            val filePath = if (isFromTest) {
                "lab/lost_in_time.mp3"
            } else {
                InputUtils.getString("↪ Enter remaining BGM path", true)
            }
            val bgmFile = File(filePath)
            if (bgmFile.exists()) {
                finalBgms.add(bgmFile)
                updateBgmStatus()
            } else {
                println("File doesn't exist : ${bgmFile.absolutePath}")
                updateBgmStatus()
            }

        } else {
            println("✔️ BGM Passed")
            println("↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️")
        }

    }

    fun getBgmFiles(): List<File> {
        return finalBgms
    }

}