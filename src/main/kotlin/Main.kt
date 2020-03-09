import com.theapache64.automotion.core.*
import com.theapache64.automotion.utils.ComplexCommandExecutor
import com.theapache64.automotion.utils.DateTimeUtils
import com.theapache64.automotion.utils.InputUtils
import java.io.File
import java.lang.Exception
import kotlin.system.exitProcess

object Main {

    @JvmStatic
    fun main(args: Array<String>) {

        // Checking if all necessary dependencies are available
        if (isAllDepsAvailable()) {
            try {
                val cp = CommandParser(args)

                if (cp.isPrintHelp()) {
                    cp.printHelp()
                } else {

                    // Reading input from console
                    val inputVideos = cp.getInputVideos()
                    val bgmFiles = cp.getBgms()
                    val minTimelapseSourceLength = cp.getMinTimelapseSourceLength()
                    val timelapseSpeed = cp.getTimelapseSpeed()
                    val videoLanguage = cp.getVideoLanguage()
                    val introDuration = cp.getIntroDuration()
                    val creditsDuration = cp.getCreditsDuration()
                    val watermark = cp.getWatermark()
                    val introTitle = cp.getIntroTitle()
                    val introSubTitle = cp.getIntroSubTitle()
                    val creditsTitle = cp.getCreditsTitle()
                    val creditsSubTitle = cp.getCreditsSubTitle()
                    val fontFile = cp.getFontFile()
                    val highlightSection = cp.getHighlightSection()
                    val watermarkTextColor = cp.getWatermarkTextColor()
                    val watermarkFontSize = cp.getWatermarkFontSize()
                    val watermarkBgColor = cp.getWatermarkBgColor()
                    val watermarkBgOpacity = cp.getWatermarkBgOpacity()
                    val titleFontSize = cp.getTitleFontSize()
                    val subTitleFontSize = cp.getSubTitleFontSize()
                    val titleColor = cp.getTitleColor()
                    val subTitleColor = cp.getSubTitleColor()
                    val bgColor = cp.getBgColor()
                    val isKeepSh = cp.isKeepSh()
                    val isRawFFmpeg = cp.isRawFFmpeg()
                    val rawSrt = cp.getRawSrt()
                    val isSuperFast = cp.isSuperFast()
                    var defaultSrt = cp.getDefaultSrt(inputVideos.first())


                    // Checking if there's multiple videos
                    val inputVideo = if (inputVideos.size > 1) {
                        println("\uD83C\uDF00 Merging video files...")
                        val mergedVideo = VideoMerger.mergeVideo(inputVideos)
                        println("✔️ Video merging finished")
                        mergedVideo
                    } else {
                        inputVideos.first()
                    }



                    if (defaultSrt != null) {
                        println("🤗 We've found a connected subtitle with the video, Do you want to proceed?")
                        val isProceedWithDefault = InputUtils.getString(
                            "y for YES and other for NO",
                            true
                        ).toLowerCase() == "y"

                        if (!isProceedWithDefault) {
                            defaultSrt = null
                        }
                    }

                    // Downloading subtitles
                    val autoSubNodes = when {

                        rawSrt != null -> {
                            println("\uD83D\uDD0A Analyzing subtitles ... ")
                            val subNodes = AutoSubUtils.getSubFromSrt(rawSrt)
                            println("✔️ Subtitle analysis finished")
                            subNodes

                        }

                        defaultSrt != null -> {
                            require(inputVideos.size == 1) { "Default subtitle option can't be used with multiple input video files." }
                            println("\uD83D\uDD0A Analyzing default subtitles... ")
                            val subNodes = AutoSubUtils.getSubFromSrt(defaultSrt)
                            println("✔️ Subtitle analysis finished (default)")
                            subNodes
                        }

                        else -> {
                            // use autosub as last resort
                            println("\uD83D\uDD0A Analyzing audio stream... ")
                            val subNodes = AutoSubUtils.getSubFor(inputVideo, videoLanguage)
                            println("✔️ Audio analysis finished")
                            subNodes
                        }
                    }

                    println("\uD83C\uDFA5 Analyzing video stream...")
                    val subAnalyzer =
                        SubtitleAnalyzer(
                            autoSubNodes,
                            minTimelapseSourceLength, timelapseSpeed, introDuration
                        )
                    val subReport = subAnalyzer.getReport()
                    println("✔️ Video analysis finished")
                    println("\uD83C\uDFB8 Analyzing BGM...")
                    val bgmAgent = BgmAgent(
                        bgmFiles,
                        subReport.totalTimelapseDuration + introDuration + creditsDuration,
                        false
                    )
                    println("✔️ BGM analysis finished")


                    val goodBgmFiles = bgmAgent.getBgmFiles()
                    val bgmFile = if (goodBgmFiles.size > 1) {
                        println("\uD83C\uDF00 Merging BGM files...")
                        val mergedFile = AudioMerger(goodBgmFiles).merge()
                        println("✔️ BGM merging finished")
                        mergedFile
                    } else {
                        goodBgmFiles.first()
                    }

                    println("⚛️ Preparing FFMPEG commands...")
                    val commandCook = CommandCook(
                        inputVideo,
                        bgmFile,
                        subReport,
                        introDuration,
                        creditsDuration,
                        timelapseSpeed,
                        watermark,
                        watermarkTextColor,
                        watermarkFontSize,
                        watermarkBgColor,
                        watermarkBgOpacity,
                        introTitle,
                        introSubTitle,
                        creditsTitle,
                        creditsSubTitle,
                        titleFontSize,
                        subTitleFontSize,
                        titleColor,
                        subTitleColor,
                        fontFile,
                        bgColor,
                        isRawFFmpeg,
                        isSuperFast,
                        highlightSection
                    )

                    val command = commandCook.prepareCommand()
                    println("✔️ Commands prepared")
                    println("↪ No of timelapses: ${subReport.timelapses.size}")
                    if (subReport.timelapses.isNotEmpty()) {
                        println("↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️")
                    }
                    for (timelapse in subReport.timelapses) {
                        val duration = DateTimeUtils.getTimeFormatted(timelapse.sourceEnd - timelapse.sourceStart)

                        val targetDetails = "@ [${DateTimeUtils.getTimeFormatted(
                            timelapse.targetStart
                        )} - ${DateTimeUtils.getTimeFormatted(timelapse.targetEnd)}]"

                        val sourceDetails =
                            "\uD83D\uDD5B [${DateTimeUtils.getTimeFormatted(timelapse.sourceStart)} - ${DateTimeUtils.getTimeFormatted(
                                timelapse.sourceEnd
                            )}]"

                        println(
                            "$sourceDetails \uD83C\uDFA5 $duration \uD83D\uDC41️ ${timelapse.targetDuration.toInt()}sec $targetDetails"
                        )
                    }
                    if (subReport.timelapses.isNotEmpty()) {
                        println("↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️↔️")
                    }

                    val commandSh = File("${inputVideo.absoluteFile.parentFile.absolutePath}/${inputVideo.name}.sh")
                    commandSh.delete()
                    commandSh.writeText(command)
                    println("\uD83D\uDCBE Command saved to ${commandSh.absolutePath}")

                    println("⚒ Executing command...")
                    ComplexCommandExecutor.executeCommand(
                        "sh \"${commandSh.absolutePath}\"",
                        true,
                        { true },
                        { false },
                        isSuppressError = true,
                        isReturnAll = true,
                        prefix = "\uD83D\uDCBF ",
                        isClearAfterFinish = true
                    )
                    println("✔️ Commands executed")
                    println("\uD83C\uDF89 File saved to file://${commandCook.outputFileName}")
                    if (!isKeepSh) {
                        commandSh.delete()
                    }

                    if (commandCook.highLightFile != null) {
                        // Delete highlight video
                        commandCook.highLightFile!!.deleteOnExit()
                    }

                    exitProcess(0)

                }
            } catch (e: Exception) {
                e.printStackTrace()
                exitProcess(0)
            }
        } else {
            println("ERROR: Dependency error")
        }
    }

    private fun isAllDepsAvailable(): Boolean {
        return DependencyChecker.isFFmpegOkay() &&
                DependencyChecker.isAutoSubOkay() &&
                DependencyChecker.isFFPBOkay()
    }


}